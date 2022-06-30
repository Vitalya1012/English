package com.example.english.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.english.pojo.Word;

import java.util.List;

@Dao
public interface WordsDao {
    @Query("SELECT * FROM words")
    LiveData<List<Word>> getAllWords();

    @Insert
    void insertWord(Word word);

    @Update
    void changeWord(Word word);

    @Delete
    void deleteWord(Word word);

    @Query("DELETE FROM words")
    void deleteAllWords();
}
