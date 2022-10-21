package com.example.finalproject.data.local.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Fav.class}, version = 1)
public abstract class FavDataBase extends RoomDatabase {

    private static FavDataBase instance;

    public abstract FavDoa Doa();

    public static FavDataBase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(), FavDataBase.class,"Fav_table")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }


}
