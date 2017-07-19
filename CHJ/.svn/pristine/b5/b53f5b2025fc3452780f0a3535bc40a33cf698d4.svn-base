package com.lubin.chj.utils;

import com.lubin.chj.service.ScanServiceWithUHF;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {
	public static final String ActionOffScreen = "android.intent.action.SCREEN_OFF";// 屏幕被关闭之后的广播
	public static final String ActionOnScreen = "android.intent.action.SCREEN_ON";// 屏幕被打开之后的广播

	@Override
	public void onReceive(Context context, Intent intent) {
		ScanServiceWithUHF dev = ScanServiceWithUHF.getInstance();
		if (ActionOffScreen == intent.getAction()) {
			dev.Close();
		} else if (ActionOnScreen == intent.getAction()) {
			dev.Open();
		}
	}
}