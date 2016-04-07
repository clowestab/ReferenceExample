package com.doublenegative.referenceexample.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.doublenegative.referenceexample.Models.Thing;
import com.doublenegative.referenceexample.R;

/**
 * Created by thomasclowes on 29/01/16.
 */
public class ExtraActivity extends Activity {

    Thing thing;

    //region Lifecycle methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_two);

        Intent intent = getIntent();
        thing = intent.getParcelableExtra("thing");

        Log.d("REFERENCE", "New activity has thing with key " + thing.key);


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms

                Log.d("REFERENCE", "New activity has thing with key " + thing.key + " after 6 seconds");
            }
        }, 6000);
    }
}
