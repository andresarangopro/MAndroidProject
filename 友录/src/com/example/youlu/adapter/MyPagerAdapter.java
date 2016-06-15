package com.example.youlu.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.youlu.ui.CalllogFragment;
import com.example.youlu.ui.ContactFragment;
import com.example.youlu.ui.DialpadFragment;
import com.example.youlu.ui.SmsFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyPagerAdapter  extends FragmentPagerAdapter{

	List<Fragment> fragments;
	
	public MyPagerAdapter(FragmentManager fm) {
		super(fm);
		fragments = new ArrayList<Fragment>();
		fragments.add(new CalllogFragment());
		fragments.add(new ContactFragment());
		fragments.add(new SmsFragment());
		fragments.add(new DialpadFragment());
	}
	
	public MyPagerAdapter(FragmentManager fm,List<Fragment> fragments){
		super(fm);
		this.fragments = fragments;
	}

	@Override
	public Fragment getItem(int position) {
		if(fragments.get(position)!=null){
			return fragments.get(position);
		}
		return null;
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

}
