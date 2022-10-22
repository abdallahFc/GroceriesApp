package com.example.finalproject.data.local.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

@Dao
public interface FavDoa {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertItem(Fav fav);

    @Query("DELETE FROM Fav_table")
    Completable deleteItem();

    @Query("SELECT * FROM Fav_table")
    Observable<List<Fav>> getAllItems();
}
