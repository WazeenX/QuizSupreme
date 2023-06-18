package com.example.quiz1;

import static com.example.quiz1.AboutActivity.MyPREFERENCES;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.WindowManager;

public class JoinRoomActivity extends AppCompatActivity {

    public static int checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //screenshot protection
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        setContentView(R.layout.activity_join_room);

        SharedPreferences sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        int default1;
        switch (Configuration.UI_MODE_NIGHT_MASK) {
            case AppCompatDelegate.MODE_NIGHT_NO:
                default1 = 0;
                break;
            case AppCompatDelegate.MODE_NIGHT_YES:
                default1 = 0;
                break;
            default:
                default1 = 2;
        }

        checked = sharedPreferences.getInt("checked", default1);
        switch (checked) {
            case 0:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case 1:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case 2:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
            case 3:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY);
                break;
        }
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(JoinRoomActivity.this, MainActivity.class));
        finish();
    }
}