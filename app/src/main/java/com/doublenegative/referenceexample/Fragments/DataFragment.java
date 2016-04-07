package com.doublenegative.referenceexample.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.doublenegative.referenceexample.Models.Thing;
import com.doublenegative.referenceexample.R;

/**
 * Created by thomasclowes on 29/01/16.
 */
public class DataFragment extends Fragment {

    Thing thing;


    public static DataFragment newInstance(Thing thing) {

        DataFragment fragment = new DataFragment();

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

        //no view
        return null;
    }
}
