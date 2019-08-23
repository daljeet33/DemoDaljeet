package com.example.dimagibot.ui.main;

import com.example.dimagibot.data.persistence.UserCommand;

import java.util.List;

public interface MainMvpPresenter {

    void onUserCommandInput(String command);

    void onHistoryFetched(List<UserCommand> userCommands);
}
