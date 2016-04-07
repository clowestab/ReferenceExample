package com.doublenegative.referenceexample.Activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

import com.doublenegative.referenceexample.Fragments.DataFragment;
import com.doublenegative.referenceexample.Fragments.MainFragment;
import com.doublenegative.referenceexample.Models.Thing;
import com.doublenegative.referenceexample.R;

/**
 * Created by thomasclowes on 29/01/16.
 */
public class IndexActivity extends Activity {

    public Thing thing = new Thing();

    //Fragment tags
    public static final String MAIN_FRAG_TAG = "mainFragment";
    public static final String DATA_FRAG_TAG = "dataFragment";

    public DataFragment dataFragment;


    //region Lifecycle methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.container_view);

        //Get the fragment manager
        FragmentManager fragmentManager = getFragmentManager();

        //see if we have a dataFragment instance
        dataFragment = (DataFragment)
                fragmentManager.findFragmentByTag(DATA_FRAG_TAG);

        //if we dont have a data fragment
        if (dataFragment == null) {

            //create one
            FragmentTransaction transactionTwo = fragmentManager.beginTransaction();
            dataFragment = DataFragment.newInstance(thing);
            transactionTwo.add(dataFragment, DATA_FRAG_TAG);

            //add it
            transactionTwo.commit();

            //need to execute now.. otherwise fragments onCreate not called and we wont
            //be able to get a dependency factory below
            fragmentManager.executePendingTransactions();
        }


        //create and attach the initial container fragment
        MainFragment fragment = (MainFragment)
                fragmentManager.findFragmentByTag(MAIN_FRAG_TAG);

        //if the fragment has not been created or retained
        if (fragment == null) {

            //create it
            fragment = MainFragment.newInstance(thing);
        }

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container_framelayout, fragment, MAIN_FRAG_TAG);

        transaction.commit();

        Log.d("REFERENCE", "The initial value in the activity is " + thing.key);
    }
}
