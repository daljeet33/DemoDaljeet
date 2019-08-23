package com.example.dimagibot.data.persistence;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(UserCommand userCommand);

    @Query("SELECT * FROM UserCommand where userName=:username ORDER BY created_time ASC" )
    List<UserCommand> getUserCommands(String username);


}
