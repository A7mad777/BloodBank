package com.example.future.smarthome.Fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.future.smarthome.Activities.HomeActivity;
import com.example.future.smarthome.Activities.HomePager;
import com.example.future.smarthome.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment implements ViewPager.OnPageChangeListener {
    TabLayout tabLayout;
    ViewPager pager;


    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        tabLayout = view.findViewById(R.id.tabLayout2);
        pager = view.findViewById(R.id.viewPager2);
        pager.setOffscreenPageLimit(2);
        HomePager homePager = new HomePager(getFragmentManager());
        pager.setAdapter(homePager);
        tabLayout.setupWithViewPager(pager);
        pager.addOnPageChangeListener(this);
        return view;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
