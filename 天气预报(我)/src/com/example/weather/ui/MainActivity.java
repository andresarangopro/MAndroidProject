package com.example.weather.ui;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weather.R;
import com.example.weather.bean.WeatherBean;
import com.example.weather.bean.WeatherBean.Result.Data.RealTime;
import com.example.weather.bean.WeatherBean.Result.Data.Weather;
import com.example.weather.content.Config;
import com.example.weather.server.WeatherService;
import com.example.weather.server.WeatherService.MyIBinder;
import com.google.gson.Gson;
import com.natasa.progressviews.CircleSegmentBar;
import com.natasa.progressviews.utils.ProgressStartPoint;

public class MainActivity extends MyBaseActvity{
	ImageView ivWeather;
	TextView weather,temperature,humidity,direct,power,date,moon,city,temp;
	ServiceConnection conn;
	WeatherService service;
	Intent intent ;
	MyReceiver myReceiver ;
	private View headerView;

	CircleSegmentBar  segmentDay;
	CircleSegmentBar  segmentNight;
	private Runnable mTimer1;  
	private Runnable mTimer2;  
	protected Handler mHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mHandler = new Handler();
		init();
		initTitle();
		initReceiver();
		initServer();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	private void updateWeather(String string) {
		Gson gson = new Gson();
		WeatherBean bean = gson.fromJson(string, WeatherBean.class);
		Log.d("Tag", bean.toString());
		RealTime realtime = bean.getResult().getData().getRealtime();
		moon.setText(realtime.getMoon());
		temperature.setText(realtime.getWeather().getTemperature()+"¡ã");
		humidity.setText("Êª¶È£º"+realtime.getWeather().getTemperature()+"%");
		moon.setText(realtime.getMoon());
		city.setText(realtime.getCity_name());
		direct.setText(realtime.getWind().getDirect()+":");
		power.setText(realtime.getWind().getPower());
		weather.setText(realtime.getWeather().getInfo());
		setTimer(bean.getResult().getData().getWeather().get(0));
		setImage(bean.getResult().getData().getWeather().get(0));
	}

	private void initServer() {
		intent = new Intent(this,WeatherService.class);
		conn = new ServiceConnection() {
			@Override
			public void onServiceDisconnected(ComponentName arg0) {

			}
			@Override
			public void onServiceConnected(ComponentName arg0, IBinder binder) {
				service = ((MyIBinder) binder).getService();
			}
		};
		startService(intent);
		bindService(intent, conn, Context.BIND_AUTO_CREATE);
	}


	private void initTitle() {
		ImageView ivLeft = setHeaderViewImage(headerView, R.drawable.side, Position.LEFT);
		ivLeft.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent2 = new Intent(MainActivity.this,DemoActivity.class);
				startActivity(intent2);
			}
		});
		setHeaderViewImage(headerView, R.drawable.download, Position.RIGHT);
	}

	private void init() {
		weather = (TextView) findViewById(R.id.tv_main_weather);
		temperature= (TextView) findViewById(R.id.tv_main_temperature);
		humidity= (TextView) findViewById(R.id.tv_main_humidity);
		direct= (TextView) findViewById(R.id.tv_main_direct);
		power = (TextView) findViewById(R.id.tv_main_power);
		date = (TextView) findViewById(R.id.tv_main_date);
		moon = (TextView) findViewById(R.id.tv_main_moon);
		city= (TextView) findViewById(R.id.tv_main_city);
		temp = (TextView) findViewById(R.id.tv_main_dayAndNight_temp);

		ivWeather = (ImageView) findViewById(R.id.iv_main_weather);
		headerView = findViewById(R.id.title_mainn);

		segmentDay = (CircleSegmentBar) findViewById(R.id.csb_main_day);
		//you can set for every ProgressView width, progress background width, progress bar line width
		segmentDay.setCircleViewPadding(2);
		segmentDay.setWidthProgressBackground(1);
		segmentDay.setWidthProgressBarLine(15);
		//you can set start position for progress
		segmentDay.setStartPositionInDegrees(ProgressStartPoint.BOTTOM);
		//you can set linear gradient with default colors or to set yours colors, or sweep gradient
		segmentDay.setLinearGradientProgress(false);

		segmentNight = (CircleSegmentBar) findViewById(R.id.csb_main_night);
		//you can set for every ProgressView width, progress background width, progress bar line width
		segmentNight.setCircleViewPadding(2);
		segmentNight.setWidthProgressBackground(1);
		segmentNight.setWidthProgressBarLine(15);
		//you can set start position for progress
		segmentNight.setStartPositionInDegrees(ProgressStartPoint.BOTTOM);
		//you can set linear gradient with default colors or to set yours colors, or sweep gradient
		segmentNight.setLinearGradientProgress(false);
	}

	private void initReceiver() {
		myReceiver = new MyReceiver();
		IntentFilter intentFilter= new IntentFilter();
		intentFilter.addAction(Config.UPDATE_DATE);
		intentFilter.addAction(Config.UPDATE_WEATHER);
		registerReceiver(myReceiver, intentFilter);
	}

	private void setTimer(Weather weather) {
		String day= weather.getInfo().getDay().get(2);
		String night= weather.getInfo().getNight().get(2);
		temp.setText(day+"¡ãC"+"/"+night+"¡ãC");
		final int dayTemp = Integer.parseInt(day);
		final int nightTemp = Integer.parseInt(night);
		mTimer1 = new Runnable() {
			int progress1;
			@Override
			public void run() {
				progress1 += 1;
				if (progress1 <= ((100*dayTemp)/50))
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							segmentDay.setProgress((float) progress1);
							segmentDay.setText("");
						}
					});
				mHandler.postDelayed(this, 100);
			}
		};
		mTimer2 = new Runnable() {
			int progress2;
			@Override
			public void run() {
				progress2 += 1;
				if (progress2 <= ((100*nightTemp)/50))
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							segmentNight.setProgress((float) progress2);
							segmentNight.setText("" );
						}
					});
				mHandler.postDelayed(this, 100);
			}
		};
		mHandler.postDelayed(mTimer1, 1000);
		mHandler.postDelayed(mTimer2, 1000);
	}


	private void setImage(Weather weather) {
		String date = new SimpleDateFormat("HH").format(new Date());
		int time = Integer.parseInt(date);
		String condition = null;
		if(time<18&&time>6){
			condition = weather.getInfo().getDay().get(0);
		}else{
			condition = weather.getInfo().getNight().get(0);
		}
		if(condition.equals("0")){
			ivWeather.setImageResource(R.drawable.sunny);
		}else if(condition.equals("1")){
			ivWeather.setImageResource(R.drawable.cloudy);
		}else if(condition.equals("2")){
			ivWeather.setImageResource(R.drawable.lotcloudy);
		}else{
			ivWeather.setImageResource(R.drawable.rain);
		}
	}

	public class MyReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if(action==Config.UPDATE_DATE){
				String dateString = intent.getStringExtra("date");
				date.setText(dateString);
			}
			if(action==Config.UPDATE_WEATHER){
				String weatherString = intent.getStringExtra("weather");
				updateWeather(weatherString);
			}
		}
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unbindService(conn);
		stopService(intent);
		unregisterReceiver(myReceiver);
	}
}
