package com.example.dimagibot.data.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

public class DatabaseHelper {

    private Context context;
    private static DatabaseHelper instance;

    private AppDatabase appDatabase;

    private DatabaseHelper(Context context){
        this.context=context;
        appDatabase= Room.databaseBuilder(context,AppDatabase.class,"UserCommands").build();
    }

    public static synchronized DatabaseHelper getInstance(Context context){
        if(instance==null){
            instance=new DatabaseHelper(context);
        }
        return instance;
    }

    public AppDatabase getAppDatabase(){
        return appDatabase;
    }

}
