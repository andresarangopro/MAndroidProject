package com.example.weixin.ui.fragment;

import com.example.weixin.R;
import com.example.weixin.ui.CircleActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class DiscoveryFragment extends FragmentBase {

	LinearLayout circle_layout;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_discovery, container, false);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		init();
	}

	private void init(){
		circle_layout = (LinearLayout) findViewById(R.id.ll_discovery_toCircle);
		circle_layout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),CircleActivity.class);
				startActivity(intent);
			}
		});
	}
}
