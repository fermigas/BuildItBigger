package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.util.Log;

import java.util.concurrent.TimeUnit;


public class EndpointsAsyncTaskTest extends AndroidTestCase {

    EndpointsAsyncTask mEndpointsAsyncTask;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mEndpointsAsyncTask = new EndpointsAsyncTask(){

            // These are necessary to prevent unwanted interaction with mainActivity
            @Override
            protected void onPreExecute(){
            }

            @Override
            protected void onPostExecute(String result){
            }
        };
    }

    public void testEndpointAsyncReturnsWithin20Seconds() {

        try {
            mEndpointsAsyncTask.execute();
            String joke = mEndpointsAsyncTask.get(20, TimeUnit.SECONDS);

            // assertNull(joke);   // for testing failure case first
            assertNotNull(joke);

            if(joke != null)
                Log.e("JokeEndpointTest", " ==== Didn't time out.  Joke: " + joke);


        } catch (Exception e) {
            fail("No data was received within 20 seconds.");
        }
    }

    public void testEndpointAsyncReturnsExpectedData() {

        try {
            mEndpointsAsyncTask.execute();
            String joke = mEndpointsAsyncTask.get(20, TimeUnit.SECONDS);
            assertEquals("Cloudy with a side of meatballs.", joke);

            if(joke != null)
                Log.e("JokeEndpointTest", " ==== Got expected data.  Joke: " + joke);

        } catch (Exception e) {
            fail("The wrong data was returned.");
        }
    }
}
