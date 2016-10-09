package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;

import com.example.jon.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

class EndpointsAsyncTask extends AsyncTask<String, String, String> {
    private MainActivity mainActivity;
    private MyApi myApiService = null;


    EndpointsAsyncTask() {}  // Required for testing

    EndpointsAsyncTask(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if (mainActivity.spinner != null) {
            mainActivity.spinner.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected String doInBackground(String... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl(mainActivity.getString(R.string.local_endpoint_api))
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        try {
            return myApiService.sayHi().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {

        if (mainActivity.spinner != null) {
            mainActivity.spinner.setVisibility(View.GONE);
        }

        Intent intent = new Intent(mainActivity.getApplicationContext(), com.example.androidjokelib.JokeActivity.class);
        intent.putExtra("android_joke", result);
        mainActivity.startActivity(intent);

    }
}
