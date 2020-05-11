package com.example.future.smarthome.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.future.smarthome.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Moon extends Fragment {

TextView textView;
    public Moon() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_moon, container, false);
        return view;
    }
}
