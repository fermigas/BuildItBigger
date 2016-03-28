package com.example.androidjokelib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        String joke = getIntent().getStringExtra("android_joke");

        TextView tv = (TextView) findViewById(R.id.joke_text_view);
        tv.setText(joke);

    }
}
