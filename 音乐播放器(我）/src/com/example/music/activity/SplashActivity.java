package com.example.music.activity;

import com.example.activity.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class SplashActivity extends Activity{
	
	LinearLayout animContainer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		initView();
		initAnim();
	}
	/*
	 * 动画相关
	 * **/
	
	private void initAnim() {
		Animation anim = AnimationUtils.loadAnimation(this, R.anim.splash_anim);
		Log.d("TAG", anim.toString());
		animContainer.startAnimation(anim);
//		Intent intent = new Intent(this,MainActivity.class);
//		startActivity(intent);
//		finish();
//		第一种方式
//		new Handler().postDelayed(new Runnable() {
//			@Override
//			public void run() {
//				Intent intent = new Intent(SplashActivity.this,MainActivity.class);
//				startActivity(intent);
//				finish();				
//			}
//		}, 3000);
		anim.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				Intent intent = new Intent(SplashActivity.this,MainActivity_.class);
				startActivity(intent);
				finish();	
			}
		});
	}
	/**
	 * 初始化各种视图
	 * */
	
	private void initView() {
		animContainer = (LinearLayout) findViewById(R.id.ll_splash);
	}
}