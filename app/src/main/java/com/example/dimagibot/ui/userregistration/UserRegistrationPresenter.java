package com.example.dimagibot.ui.userregistration;

public class UserRegistrationPresenter implements UserRegistrationMvpPresenter {
    private UserRegistrationMvpView userRegistrationMvpView;

    UserRegistrationPresenter(UserRegistrationMvpView userRegistrationMvpView){
        this.userRegistrationMvpView = userRegistrationMvpView;
    }
    @Override
    public void onEnterClicked(String userName) {
        if(userName.isEmpty())userRegistrationMvpView.showToast("Please enter a user name",0);
        else{
            userRegistrationMvpView.moveToMainActivity(userName);

        }

    }
}
