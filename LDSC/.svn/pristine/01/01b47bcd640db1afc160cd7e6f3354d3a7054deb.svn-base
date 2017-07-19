package com.lide.app.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lide.app.ui.FragmentBase;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<FragmentBase> fragments = new ArrayList<FragmentBase>();


    public ViewPagerAdapter(FragmentManager fm, List<FragmentBase> fragments) {
        super(fm);
        this.fragments = fragments;

    }
    
    @Override
    public Fragment getItem(int position) {
        if (fragments.size() > 0) {
            return fragments.get(position);
        }
        throw new IllegalStateException("No fragment at position " + position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}
