package com.example.dimagibot.ui.userregistration;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dimagibot.R;
import com.example.dimagibot.config.Constants;
import com.example.dimagibot.ui.base.BaseActivity;
import com.example.dimagibot.ui.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class UserRegistrationActivity extends BaseActivity implements UserRegistrationMvpView {

    @BindView(R.id.welcome_text)
    TextView welcomeText;
    @BindView(R.id.user_name_edit_text)
    EditText userNameEditText;
    @BindView(R.id.btn_register)
    Button btnRegister;
    private UserRegistrationMvpPresenter userRegistrationMvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        ButterKnife.bind(this);
        userRegistrationMvpPresenter=new UserRegistrationPresenter(this);
    }

    @OnClick(R.id.btn_register)
    public void onViewClicked() {
        userRegistrationMvpPresenter.onEnterClicked(userNameEditText.getText().toString());

    }

    @Override
    public void moveToMainActivity(String userName) {
        Intent intent=new Intent(this, MainActivity.class);
        intent.putExtra(Constants.USERNAME,userName);
        startActivity(intent);
    }
}
