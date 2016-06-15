package com.example.youlu.biz;

import android.content.Context;
import android.os.Bundle;

import com.example.youlu.listener.OnLoadSmsesFinshListener;
import com.example.youlu.util.YouluUtils;

public class ChatBiz {
	private Context context;

	public ChatBiz(Context context){
		this.context = context; 
	}

	public void asyncGetAllSmses(OnLoadSmsesFinshListener listener,int thread_id) {
		YouluUtils.asyncGetAllSmses(context,listener,thread_id);
	}
	public void sendSms(String number,String string){
		YouluUtils.sendSms(context,number,string);
	}

	public void saveSendSms(long date, String body, String number, int thread_id) {
		YouluUtils.saveSendSms(context,date,body,number,thread_id);
	}

	public void receiverSms(Bundle extras, int thread_id) {
		YouluUtils.receiverSms(context,extras,thread_id);
	}
	
	public String getReceiveNumber(Bundle bundle){
		return YouluUtils.getReceiveNumber(context, bundle);
	}
}
