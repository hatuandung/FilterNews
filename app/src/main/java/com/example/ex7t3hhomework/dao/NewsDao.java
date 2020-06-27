package com.example.ex7t3hhomework.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ex7t3hhomework.model.News;

import java.util.List;
@Dao
public interface NewsDao {
    @Query("SELECT * FROM News")
    List<News> getAll();

    @Query("SELECT * FROM News WHERE favorite = 1")
    List<News> getFavorite();

    @Insert
    void insert(News... news);

    @Update
    void update(News... news);

    @Delete
    void delete(News... news);

    @Query("DELETE FROM News")
    void deleteAll();
}
