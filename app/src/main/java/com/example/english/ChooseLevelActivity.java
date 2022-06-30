package com.example.english;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChooseLevelActivity extends AppCompatActivity {

    private int lang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_level);
        Intent intent = getIntent();
        lang = intent.getIntExtra("lang",0);
    }

    public void onClickToA1(View view) {
        Intent intent = new Intent(this, LearnActivity.class);
        intent.putExtra("lang",lang);
        intent.putExtra("level", 1);
        startActivity(intent);
    }

    public void onClickToA2(View view) {
        Intent intent = new Intent(this, LearnActivity.class);
        intent.putExtra("lang",lang);
        intent.putExtra("level", 2);
        startActivity(intent);
    }

    public void onClickToB1(View view) {
        Intent intent = new Intent(this, LearnActivity.class);
        intent.putExtra("lang",lang);
        intent.putExtra("level", 3);
        startActivity(intent);
    }

    public void onClickToB2(View view) {
        Intent intent = new Intent(this, LearnActivity.class);
        intent.putExtra("lang",lang);
        intent.putExtra("level", 4);
        startActivity(intent);
    }

    public void onClickToC1(View view) {
        Intent intent = new Intent(this, LearnActivity.class);
        intent.putExtra("lang",lang);
        intent.putExtra("level", 5);
        startActivity(intent);
    }

    public void onClickToFavorite(View view) {
        Intent intent = new Intent(this, LearnActivity.class);
        intent.putExtra("lang",lang);
        intent.putExtra("level", 0);
        startActivity(intent);
    }
}