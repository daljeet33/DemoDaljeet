package com.example.dimagibot.ui.base;

public interface BaseMvpView {


    void showToast(String text, int length);
     void showToast(int id, int length);

    boolean isNetworkConnected();

    void hideKeyboard();


}
