package com.example.english.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.english.pojo.Word;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private static WordDatabase database;

    private LiveData<List<Word>> words;

    public MainViewModel(@NonNull Application application) {
        super(application);
        database = WordDatabase.getInstance(getApplication());
        words = database.wordsDao().getAllWords();
    }

    public LiveData<List<Word>> getWords() {
        return words;
    }

    public void insertWord(Word word) {
        new InsertTask().execute(word);
    }

    public void changeWord(Word word) {
        new ChangeTask().execute(word);
    }

    public void deleteWord(Word word) {
        new DeleteTask().execute(word);
    }

    public void deleteAllWord() {
        new DeleteAllTask().execute();
    }

    private static class InsertTask extends AsyncTask<Word, Void, Void> {

        @Override
        protected Void doInBackground(Word... words) {
            if (words != null && words.length > 0) {
                database.wordsDao().insertWord(words[0]);
            }
            return null;
        }
    }

    private static class ChangeTask extends AsyncTask<Word, Void, Void> {

        @Override
        protected Void doInBackground(Word... words) {
            if (words != null && words.length > 0) {
                database.wordsDao().changeWord(words[0]);
            }
            return null;
        }
    }


    private static class DeleteTask extends AsyncTask<Word, Void, Void> {

        @Override
        protected Void doInBackground(Word... words) {
            if (words != null && words.length > 0) {
                database.wordsDao().deleteWord(words[0]);
            }
            return null;
        }
    }

    private static class DeleteAllTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... words) {
            database.wordsDao().deleteAllWords();
            return null;
        }
    }
}
