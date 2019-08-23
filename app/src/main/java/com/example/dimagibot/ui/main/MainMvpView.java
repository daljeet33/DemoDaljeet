package com.example.dimagibot.ui.main;

import com.example.dimagibot.ui.base.BaseMvpView;

public interface MainMvpView extends BaseMvpView {

    void sayTextOutLoud(String userInput);

    void addNewTextToChatList(String chatInput, String type);
    void addNewTextToChatList(int chatInput, String type);

    void fetchUserHistory();

    void saveUserCommand(String userInput);
}
