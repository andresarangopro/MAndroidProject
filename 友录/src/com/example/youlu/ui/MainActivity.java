package com.example.youlu.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.example.youlu.R;
import com.example.youlu.adapter.MyPagerAdapter;

public class MainActivity extends FragmentActivity implements OnCheckedChangeListener, OnPageChangeListener {

	ViewPager viewPager;
	RadioGroup radioGroup;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {
		viewPager = (ViewPager) findViewById(R.id.vp_mainActivity_fragmentContainer);
		viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
		viewPager.setOnPageChangeListener(this);
		
		radioGroup=(RadioGroup)findViewById(R.id.rg_mainActivity_btnContainer);
		radioGroup.setOnCheckedChangeListener(this);

		viewPager.setCurrentItem(1);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		viewPager.setCurrentItem(group.indexOfChild(group.findViewById(checkedId)),false);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
	}
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}
	@Override
	public void onPageSelected(int position) {
		radioGroup.check(radioGroup.getChildAt(position).getId());
	}


}
