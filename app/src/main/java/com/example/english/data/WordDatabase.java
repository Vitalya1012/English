package com.example.english.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.english.pojo.Word;

@Database(entities = {Word.class},version = 1,exportSchema = false)
public abstract class WordDatabase extends RoomDatabase{
    private static WordDatabase database;
    private static final String DB_NAME = "words.db";
    private static final Object LOCK = new Object();

    public static WordDatabase getInstance(Context context){
        synchronized (LOCK) {
            if (database == null) {
                database = Room.databaseBuilder(context, WordDatabase.class, DB_NAME).build();
            }
        }
        return database;
    }
    public abstract WordsDao wordsDao();
}
