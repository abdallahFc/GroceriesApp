package com.example.finalproject.data.local.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavDoa {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertItem(Fav fav);

    @Query("DELETE FROM Fav_table")
    void deleteItem();

    @Query("SELECT * FROM Fav_table")
    LiveData<List<Fav>> getAllItems();
}
