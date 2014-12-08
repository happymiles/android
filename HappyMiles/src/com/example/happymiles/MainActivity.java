package com.example.happymiles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        new Handler().postDelayed(new Runnable() {
                public void run() {
                        startActivity(new Intent(MainActivity.this, Login.class));
                        finish();
                }
        }, 5000);
        
    }
}
