package com.example.youlu.listener;

import java.util.List;

import com.example.youlu.bean.Sms;

public interface OnLoadSmsesFinshListener {
	void onLoadFinish(List<Sms> smses);
}
