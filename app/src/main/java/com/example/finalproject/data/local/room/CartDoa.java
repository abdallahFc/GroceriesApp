package com.example.finalproject.data.local.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
@Dao
public interface CartDoa {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertItem(Cart cart);

    @Query("DELETE FROM cart_table WHERE id =:id")
    void deleteItem(int id);

    @Query("SELECT * FROM cart_table")
    LiveData<List<Cart>> getAllItems();
}
