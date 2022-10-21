package com.example.finalproject.data.local.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Cart.class}, version = 1)
public abstract class CartDataBase extends RoomDatabase {

    private static CartDataBase instance;

    public abstract CartDoa Doa();

    public static CartDataBase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),CartDataBase.class,"cart_database")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }


}
