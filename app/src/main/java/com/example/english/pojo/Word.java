package com.example.english.pojo;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity (tableName = "words")
public class Word {
    @PrimaryKey (autoGenerate = true)
    private int id;
    private String english;
    private String transcription;
    private String russian;
    private int level;
    private int favorite;

    public Word(int id, String english, String transcription, String russian, int level, int favorite) {
        this.id = id;
        this.english = english;
        this.transcription = transcription;
        this.russian = russian;
        this.level = level;
        this.favorite = favorite;
    }
    @Ignore
    public Word(String english, String transcription, String russian, int level, int favorite) {
        this.english = english;
        this.transcription = transcription;
        this.russian = russian;
        this.level = level;
        this.favorite = favorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }

    public String getRussian() {
        return russian;
    }

    public void setRussian(String russian) {
        this.russian = russian;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }
}

