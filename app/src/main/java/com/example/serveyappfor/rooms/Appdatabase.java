package com.example.serveyappfor.rooms;

import android.content.Context;

import com.example.serveyappfor.models.SaveServey;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {SaveServey.class},version = 1,exportSchema = false)
public abstract  class Appdatabase extends RoomDatabase {

    private static final String DATABASE_NAME="servey.db";


    private static Appdatabase instance;

    public static Appdatabase getDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context,Appdatabase.class, DATABASE_NAME).build();
        }
        return instance;
    }


    public abstract ServeyDao serveyDao();


}
