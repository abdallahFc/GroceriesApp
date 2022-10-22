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
public interface CartDoa {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertItem(Cart cart);

    @Query("DELETE FROM cart_table WHERE id =:id")
    Completable deleteItem(int id);

    @Query("SELECT * FROM cart_table")
    Observable<List<Cart>> getAllItems();
}
