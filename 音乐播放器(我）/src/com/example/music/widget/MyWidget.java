package com.example.music.widget;



import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.example.music.bean.Music;

public class MyWidget extends AppWidgetProvider{

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d("TAG","onReceive�����������ˣ��յ��Ĺ㲥��"+intent.getAction());
		super.onReceive(context, intent);

	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		Log.d("TAG","onUpdate������������,appWidgetIds�ĳ��ȣ�"+appWidgetIds.length);
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		//Ҫ�ȴ���һ��RemoteViews�Ķ���
		new AsyncTask<Void, Void, List<Music>>(){
			
			@Override
			protected List<Music> doInBackground(Void... params) {
				
				return null;
			}
		};

	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		Log.d("TAG","onDelted������������,appWidgetIds�ĳ��ȣ�"+appWidgetIds.length);
		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onEnabled(Context context) {
		Log.d("TAG","onEnabled������������");
		super.onEnabled(context);
	}

	@Override
	public void onDisabled(Context context) {
		Log.d("TAG","onDisabled������������");
		super.onDisabled(context);
	}



}
