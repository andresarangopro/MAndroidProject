package com.example.weather.server;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.android.volley.Response.Listener;
import com.example.weather.content.Config;
import com.example.weather.util.WeatherUtil;


public class WeatherService extends Service {
	public Context context;
	Timer timer;
	Timer timerWeather;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public Listener<String> listener;
	public void onCreate() {
		super.onCreate();
		updateWeather();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		//修改Service的启动方式为非粘性
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				updateTime();
			}
		}, 0,800);

		timerWeather = new Timer();
		timerWeather.schedule(new TimerTask() {
			@Override
			public void run() {
				updateWeather();
			}
		}, 0,180000);
		return Service.START_NOT_STICKY;
	}

	private void updateTime() {
		String date = sdf.format(new Date());
		Intent intent = new Intent(Config.UPDATE_DATE);
		intent.putExtra("date", date);
		sendBroadcast(intent);
	}

	private void updateWeather() {
		WeatherUtil.testStringRequest(getApplicationContext());
	}

	@Override
	public IBinder onBind(Intent intent) {
		return new MyIBinder();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	
	public class MyIBinder extends Binder{
		public WeatherService getService(){
			return WeatherService.this;
		}
	}
}
