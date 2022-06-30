package com.example.english;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import com.example.english.adapters.WordAdapter;
import com.example.english.data.MainViewModel;
import com.example.english.pojo.Word;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class DictionaryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private MainViewModel viewModel;
    WordAdapter adapter;
    private List<Word> listOfWords;
    private int mode;
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        Intent intent = getIntent();
        mode = intent.getIntExtra("favoriteWords", 0);
        recyclerView = findViewById(R.id.recyclerView);
        textToSpeech = new TextToSpeech(DictionaryActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS){
                    textToSpeech.setLanguage(Locale.US);
                    textToSpeech.setPitch(1.0f);
                    textToSpeech.setSpeechRate(1.0f);
                } else if (i == TextToSpeech.ERROR){
                    Toast.makeText(DictionaryActivity.this, "error", Toast.LENGTH_SHORT).show();
                }

            }
        });
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        LiveData<List<Word>> words = viewModel.getWords();
        words.observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                if (mode == 0) {
                    listOfWords = words;
                } else {
                    listOfWords = words.stream().filter(word -> word.getFavorite() == 1).collect(Collectors.toList());
                }
                adapter = new WordAdapter(listOfWords);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(adapter);
                adapter.setOnWordClickListener(new WordAdapter.OnWordClickListener() {
                    @Override
                    public void onWordClick(int position) {
                        textToSpeech.speak(listOfWords.get(position).getEnglish(),0, null, null);
                    }
                });
            }
        });
    }
}