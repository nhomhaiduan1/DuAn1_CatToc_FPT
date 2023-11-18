package com.example.duan_cattoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class manhinhcho extends AppCompatActivity {
TextView appname,lottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhcho);
        appname=findViewById(R.id.appname);
        appname.animate().translationY(-1400).setDuration(2700).setStartDelay(0);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(manhinhcho.this, DangNhap.class);
                startActivity(intent);
            }
        }, 3500);
    }
}