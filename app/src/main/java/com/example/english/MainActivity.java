package com.example.english;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import com.example.english.data.MainViewModel;
import com.example.english.pojo.Word;
import com.example.english.utils.JSONUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MainViewModel viewModel;
    private int firstRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        firstRun = preferences.getInt("firstRun", 0);
        if (firstRun == 0) {
            JSONUtils.getData(this, viewModel);
            preferences.edit().putInt("firstRun", 1).apply();
        }
    }

    public void onClickToEnglish(View view) {
        Intent intent = new Intent(this, ChooseLevelActivity.class);
        intent.putExtra("lang", 0);
        startActivity(intent);
    }

    public void onClickToRussian(View view) {
        Intent intent = new Intent(this, ChooseLevelActivity.class);
        intent.putExtra("lang", 1);
        startActivity(intent);
    }

    public void onClickToDictionary(View view) {
        Intent intent = new Intent(this, DictionaryActivity.class);
        intent.putExtra("favoriteWords", 0);
        startActivity(intent);
    }

    public void onClickToFavoriteDictionary(View view) {
        Intent intent = new Intent(this, DictionaryActivity.class);
        intent.putExtra("favoriteWords", 1);
        startActivity(intent);
    }
}