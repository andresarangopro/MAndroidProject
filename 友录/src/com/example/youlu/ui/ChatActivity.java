package com.example.youlu.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.youlu.R;
import com.example.youlu.adapter.MyChatAdapter;
import com.example.youlu.bean.Sms;
import com.example.youlu.biz.ChatBiz;
import com.example.youlu.listener.OnLoadSmsesFinshListener;

public class ChatActivity  extends Activity{
	int thread_id;
	String name;
	int photo_id;
	String number;
	
	View headerView;
	ListView listView;
	EditText editText;
	Button btn; 
	MyReciever receiver;
	
	List<Sms> smses;
	MyChatAdapter adapter;
	private ChatBiz chatBiz;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		Intent intent = getIntent();
		thread_id = intent.getIntExtra("thread_id",0);
		name = intent.getStringExtra("name");
		photo_id = intent.getIntExtra("photo_id", 1);
		number = intent.getStringExtra("number");
		initHeaderView();
		initListView();
		initOtherView();
		
	}
	@Override
	public void onResume() {
		super.onResume();
		receiver = new MyReciever();
		IntentFilter filter = new IntentFilter();
		filter.addAction("com.tarena.SENT");
		filter.addAction("android.provider.Telephony.SMS_RECEIVED");
		filter.setPriority(1001);
		registerReceiver(receiver, filter);
		refresh();
	}
	private void refresh() {
		chatBiz.asyncGetAllSmses(new OnLoadSmsesFinshListener() {
			@Override
			public void onLoadFinish(List<Sms> smses) {
				Log.d("TAG", smses.toString());
				adapter.addAll(smses, true);
				listView.setSelection(adapter.getCount()-1);
			}
		},thread_id);
	}

	private void initOtherView() {
		chatBiz = new ChatBiz(this);
		editText = (EditText) findViewById(R.id.et_activity_chat);
		btn = (Button) findViewById(R.id.btn_activity_chat); 
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				sendSms();
			}
		});
	}

	protected void sendSms() {
		if(TextUtils.isEmpty(editText.getText().toString())){return;};
		chatBiz.sendSms(number, editText.getText().toString());
		editText.setText("");
	}
	private void initListView() {
		listView = (ListView) findViewById(R.id.lv_activity_chat_container);
		smses = new ArrayList<Sms>();
		adapter = new MyChatAdapter(this,smses,getIntent());
		listView.setAdapter(adapter);
		
	}
	
	private void initHeaderView() {
		headerView = findViewById(R.id.title_chat_activity);
		TextView tv = (TextView) headerView.findViewById(R.id.tv_headerview_middle);
		tv.setText(name);
		
		ImageView ivLeft = (ImageView) headerView.findViewById(R.id.iv_headerview_left);
		ivLeft.setVisibility(View.VISIBLE);
		ivLeft.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}
	
	public class MyReciever extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if("com.tarena.SENT".equals(action)){
				long date = intent.getLongExtra("date", 0);
				String body = intent.getStringExtra("body");
				chatBiz.saveSendSms(date,body,number,thread_id);
				refresh();
			}
			if("android.provider.Telephony.SMS_RECEIVED".equals(action)){
				if(number.equals(chatBiz.getReceiveNumber(intent.getExtras())))
				chatBiz.receiverSms(intent.getExtras(),thread_id);
				abortBroadcast();
				refresh();
			}
		}
	}
}
