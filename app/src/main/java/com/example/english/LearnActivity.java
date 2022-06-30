package com.example.english;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.english.data.MainViewModel;
import com.example.english.pojo.Word;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class LearnActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private TextView textViewWord;
    private TextView textViewTranscription;
    private TextView textViewTranslate;
    private Button buttonAddToFavorite;
    private List<Word> listOfChosenWords;
    private int currentWord;
    private int lang;
    private int level;
    private TextToSpeech textToSpeech;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        Intent intent = getIntent();
        lang = intent.getIntExtra("lang", 0);
        level = intent.getIntExtra("level", 0);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        textViewWord = findViewById(R.id.textViewWord);
        textViewTranscription = findViewById(R.id.textViewTranscription);
        textViewTranslate = findViewById(R.id.textViewTranslate);
        buttonAddToFavorite = findViewById(R.id.buttonAddToFavorite);
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS){
                    textToSpeech.setLanguage(Locale.US);
                    textToSpeech.setPitch(1.0f);
                    textToSpeech.setSpeechRate(1.0f);
                } else if (i == TextToSpeech.ERROR){
                    Toast.makeText(LearnActivity.this, "error", Toast.LENGTH_SHORT).show();
                }

            }
        });
        LiveData<List<Word>> words = viewModel.getWords();
        words.observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                if (level == 0) {
                    buttonAddToFavorite.setText(R.string.remove_from_favotite);
                    listOfChosenWords = words.stream().filter(word -> word.getFavorite() == 1).collect(Collectors.toList());
                } else {
                    buttonAddToFavorite.setText(R.string.add_to_favotite);
                    listOfChosenWords = words.stream().filter(word -> word.getLevel() == level).collect(Collectors.toList());
                }
                currentWord = (int) (Math.random() * listOfChosenWords.size());
                if (!listOfChosenWords.isEmpty()) {
                    if (lang == 0) {
                        setEnglishWord();
                    } else {
                        setRussianWord();
                    }
                }

            }
        });
    }
    public void moveToNext(View view) {
        currentWord = (int) (Math.random() * listOfChosenWords.size());
        if (!listOfChosenWords.isEmpty()) {
            textViewTranscription.setVisibility(View.INVISIBLE);
            textViewTranslate.setVisibility(View.INVISIBLE);
            if (lang == 0) {
                setEnglishWord();
            } else {
                setRussianWord();
            }
        }

    }

    public void onClickTranslate(View view) {
        textViewTranscription.setVisibility(View.VISIBLE);
        textViewTranslate.setVisibility(View.VISIBLE);
    }

    public void addToFavorites(View view) {
        if (!listOfChosenWords.isEmpty()) {
            Word word = listOfChosenWords.get(currentWord);
            if (level == 0) {
                word.setFavorite(0);
            } else
                word.setFavorite(1);
            viewModel.changeWord(word);
        }
    }

    private void setEnglishWord() {
        textViewWord.setText(listOfChosenWords.get(currentWord).getEnglish());
        textViewTranscription.setText(listOfChosenWords.get(currentWord).getTranscription());
        textViewTranslate.setText(listOfChosenWords.get(currentWord).getRussian());
    }

    private void setRussianWord() {
        textViewWord.setText(listOfChosenWords.get(currentWord).getRussian());
        textViewTranscription.setText(listOfChosenWords.get(currentWord).getTranscription());
        textViewTranslate.setText(listOfChosenWords.get(currentWord).getEnglish());
    }

    public void onClickVoice(View view) {
        textToSpeech.speak(textViewWord.getText().toString(),0, null, null);
    }
}