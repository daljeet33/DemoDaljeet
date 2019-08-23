package com.example.dimagibot.data.persistence;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {UserCommand.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
