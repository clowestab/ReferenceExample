package com.doublenegative.referenceexample.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.doublenegative.referenceexample.Activities.ExtraActivity;
import com.doublenegative.referenceexample.Activities.IndexActivity;
import com.doublenegative.referenceexample.Models.Thing;
import com.doublenegative.referenceexample.R;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by thomasclowes on 29/01/16.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    public static String SIS_THING = "sisThing";

    Thing thing;

    TextView activityThingKey;
    TextView fragmentThingKey;
    TextView dataFragmentThingKey;

    Integer changesMade = 0;

    public static MainFragment newInstance(Thing thing) {

        MainFragment fragment = new MainFragment();

        fragment.thing = thing;

        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_fragment, container, false);

        activityThingKey = (TextView) view.findViewById(R.id.activityThingKey);
        fragmentThingKey = (TextView) view.findViewById(R.id.fragmentThingKey);
        dataFragmentThingKey = (TextView) view.findViewById(R.id.dataFragmentThingKey);

        Button changeButton = (Button) view.findViewById(R.id.changeButton);
        changeButton.setOnClickListener(this);

        Button newActivityButton = (Button) view.findViewById(R.id.newActivityButton);
        newActivityButton.setOnClickListener(this);

        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        updateTextViews();
    }


    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {

        Log.d("REFERENCE", "on vsr");

        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {

            Log.d("REFERENCE", "RESTORE INSTANCE STATE");

            thing = savedInstanceState.getParcelable(SIS_THING);
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {

        Log.d("REFERENCE", "SAVE INSTANCE STATE");

        super.onSaveInstanceState(outState);

        outState.putParcelable(SIS_THING, thing);
    }


    public void updateTextViews() {

        activityThingKey.setText(((IndexActivity) getActivity()).thing.key);
        fragmentThingKey.setText(thing.key);
        dataFragmentThingKey.setText(((IndexActivity) getActivity()).dataFragment.thing.key);
    }


    @Override
    public void onClick(View view) {

        int viewId = view.getId();

        if (viewId == R.id.changeButton) {

            changesMade++;

            thing.key = "Changed " + changesMade;

            updateTextViews();

        } else if (viewId == R.id.newActivityButton) {

            Intent intent = new Intent(getActivity(), ExtraActivity.class);

            intent.putExtra("thing", thing);

            startActivity(intent);


            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Do something after 100ms

                    thing.key = "TIME DELAYED CHANGE";

                    Log.d("REFERENCE", "Updated in MainFragment after delay to " + thing.key);
                }
            }, 5000);
        }
    }
}
