package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.support.v4.util.Pair;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.JavaJokes;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings)
            return true;
        return super.onOptionsItemSelected(item);
    }

    public void tellJavaJoke(View view){
        JavaJokes javaJoke = new JavaJokes();
        String joke = javaJoke.getJoke(getString(R.string.java_key));
        Toast.makeText(this, joke, Toast.LENGTH_SHORT).show();
    }

    public void tellAndroidJoke(View view){
        JavaJokes javaJoke = new JavaJokes();
        String joke = javaJoke.getJoke(getString(R.string.android_key));
        Intent intent = new Intent(this, com.example.androidjokelib.JokeActivity.class);
        intent.putExtra("android_joke", joke);
        startActivity(intent);
    }

    public void tellGCEJoke(View view){
        new EndpointsAsyncTask(this).execute(new Pair<Context, String>(this, "Manfred"));
    }


}
