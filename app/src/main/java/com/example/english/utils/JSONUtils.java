package com.example.english.utils;

import com.example.english.pojo.Word;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class JSONUtils {

    public static void getData(Context context, MainViewModel viewModel){
        try {
            JSONObject jsonObject = new JSONObject(loadJsonObjectFromAssets(context));
            JSONArray array = jsonObject.getJSONArray("array");
            for (int i = 0; i < array.length(); i++) {
                String english = array.getJSONArray(i).getString(0);
                String transcription = array.getJSONArray(i).getString(1);
                String russian = array.getJSONArray(i).getString(2);
                int level = (Integer.parseInt(array.getJSONArray(i).getString(3)));
                int favorite = (Integer.parseInt(array.getJSONArray(i).getString(4)));
                Word word = new Word(english, transcription, russian, level, favorite);
                viewModel.insertWord(word);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    private static String loadJsonObjectFromAssets(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("words.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }
}
