package com.example.dimagibot.data.persistence;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserCommand {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "userName")
    private String username;

    @ColumnInfo(name = "command")
    private String command;

    @ColumnInfo(name = "created_time")
    private String created_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }


}
