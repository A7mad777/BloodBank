package com.example.future.smarthome.Activities;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.future.smarthome.Fragments.PostsFragment;
import com.example.future.smarthome.Fragments.donation_request_Fragment;

import java.util.ArrayList;
import java.util.List;

public class HomePager extends FragmentStatePagerAdapter {
    Fragment [] fragments = {new PostsFragment(),new donation_request_Fragment()};
    List<Fragment> fragmentList = new ArrayList<Fragment>();
    String [] str = {"المقالات","طلبات التبرع"};
    public HomePager(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new PostsFragment();
                break;
            case 1:
                fragment = new donation_request_Fragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return str[position];
    }
}
