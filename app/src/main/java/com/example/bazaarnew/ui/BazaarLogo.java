package com.example.bazaarnew.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.example.bazaarnew.R;

public class BazaarLogo extends Activity {
    private static int time=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bazaar_logo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(BazaarLogo.this, LoginActivity.class);
                startActivity (intent);
                finish();

            }
        }, time);

    }
}
