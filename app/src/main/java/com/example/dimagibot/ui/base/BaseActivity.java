package com.example.dimagibot.ui.base;

import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dimagibot.utils.NetworkUtils;
import com.example.dimagibot.utils.UiUtils;

public class BaseActivity extends AppCompatActivity implements BaseMvpView {

    private Toast toast;
    private AlertDialog progressDialog;



    @Override
    public void showToast(String text,int length) {
        if(toast!=null) toast.cancel();
        if (text != null && !text.isEmpty()) {
            toast=Toast.makeText(this, text, length);
            toast.show();
        }

    }

    @Override
    public void showToast(int id, int length) {
        showToast(getString(id),length);
    }

    @Override
    public boolean isNetworkConnected() {
      return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    @Override
    public void hideKeyboard() {
        UiUtils.hideSoftKeyboard(this);
    }
}
