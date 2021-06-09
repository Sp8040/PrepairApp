package com.example.prepairapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class StartScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        Timer timer = new Timer();
        TimerTask ts = new TimerTask() {
            @Override
            public void run() {

                boolean firstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("firstRun", true);

                if(firstRun){
                    getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("firstRun", false).apply();

                    Intent intent = new Intent(StartScreen.this, RegisterScreen.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(StartScreen.this, LoginScreen.class);
                    startActivity(intent);
                }
            }
        };

        timer.schedule(ts, 3000L);
    }

}