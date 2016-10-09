package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.support.v4.util.Pair;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.JavaJokes;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


public class MainActivity extends ActionBarActivity {

    InterstitialAd interstitialAd;
    private AdRequest adRequest;
    boolean adShowing = false;
    private Intent androidJokeIntent = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get interstitial ad going
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));

        interstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                interstitialAd.show();
            }

            @Override
            public void onAdClosed() {
                getInterstitialAd();  // get the next ad whenever we close one
                if(androidJokeIntent != null)
                    startActivity(androidJokeIntent);
            }
        });

        // load the first add
        getInterstitialAd();

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
        androidJokeIntent = new Intent(this, com.example.androidjokelib.JokeActivity.class);
        androidJokeIntent.putExtra("android_joke", joke);

        interstitialAd.loadAd(adRequest);
    }

    public void tellGCEJoke(View view){
        new EndpointsAsyncTask(this).execute();
    }

    private void getInterstitialAd(){
        adRequest = new AdRequest
                .Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

    }


}
