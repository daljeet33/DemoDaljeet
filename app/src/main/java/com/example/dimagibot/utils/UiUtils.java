package com.example.dimagibot.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AlertDialog;


public class UiUtils {



    public static void hideSoftKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view == null) view = new View(activity);
        InputMethodManager im = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (im != null) {
            im.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
