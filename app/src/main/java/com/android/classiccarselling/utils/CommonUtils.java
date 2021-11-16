package com.android.classiccarselling.utils;

import android.app.Activity;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.android.classiccarselling.ui.activity.SignUpActivity;

public class CommonUtils {


    public static void changeActivity(Activity oldActivity, Class<?> newActivity, Boolean finishOld) {
        Intent intent = new Intent(oldActivity, newActivity);
        oldActivity.startActivity(intent);
        if (finishOld) oldActivity.finish();
    }
}
