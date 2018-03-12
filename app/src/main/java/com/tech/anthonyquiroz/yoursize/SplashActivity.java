package com.tech.anthonyquiroz.yoursize;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by user on 10-Mar-18.
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        final Intent intent = new Intent(this,MainActivity.class);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run(){
                startActivity(intent);
                finish();
            }
        }, 1500);
    }
}
