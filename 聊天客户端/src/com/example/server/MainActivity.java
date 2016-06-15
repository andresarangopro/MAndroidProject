package com.example.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends Activity {

	ListView listView;
	List<String> chats;
	ArrayAdapter<String> adapter;

	EditText etNickname,etContent;

	TextView tvEmpty;

	LinearLayout llConnect,llChat;

	Socket socket ;

	BufferedReader br;

	PrintWriter pw;

	private MyReceiver receiver;

	private boolean flag = true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		listView.setAdapter(adapter);
	}

	private void initView() {
		listView = (ListView) findViewById(R.id.lv_main);
		chats = new ArrayList<String>();
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,chats);

		etNickname = (EditText) findViewById(R.id.et_main_NickName);
		etContent = (EditText) findViewById(R.id.et_main_Content);

		llConnect = (LinearLayout) findViewById(R.id.ll_main_connect);
		llChat= (LinearLayout) findViewById(R.id.ll_main_chat);

		tvEmpty  = (TextView) findViewById(R.id.tv_main_empt);

		// 注册广播接收器，用来接收MusicService发送的广播
		receiver = new MyReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Constant.ACTION_CHANG_VISIBILITY);
		filter.addAction(Constant.ACTION_UPDATE_LISTVIEW);
		registerReceiver(receiver, filter);
	}

	public void onClick(View v){
		switch (v.getId()) {
		case R.id.btn_main_connect:
			connect(); 
			break;
		case R.id.btn_main_chat:
			send();
			break;
		}
	}

	private void send() {
		final String line = etContent.getText().toString();
		if(TextUtils.isEmpty(line)){
			return;
		}
		new Thread(){
			public void run(){
				String name = etNickname.getText().toString();
				String content = etContent.getText().toString();
				pw.println(name+":"+content);
			}
		}.start();
	}

	private void connect() {
		new Thread(){
			@Override
			public void run() {
				try {
					socket = new Socket("192.168.11.145",38383);
					pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"utf8"),true);
					br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf8"));
					sendBroadcast(new Intent(Constant.ACTION_CHANG_VISIBILITY));
					while(flag){
						String resp  =br.readLine();
						if(resp==null){
							throw new RuntimeException("服务器已关闭");
						}
						chats.add(resp);
						sendBroadcast(new Intent(Constant.ACTION_UPDATE_LISTVIEW));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}		
			}
		}.start();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		try {
			br.close();
			pw.close();
			socket.close();
			flag = false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private class MyReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (Constant.ACTION_CHANG_VISIBILITY.equals(action)) {
				llConnect.setVisibility(View.GONE);
				llChat.setVisibility(View.VISIBLE);
			}
			if (Constant.ACTION_UPDATE_LISTVIEW.equals(action)) {
				listView.setVisibility(View.VISIBLE);
				tvEmpty.setVisibility(View.GONE);
				adapter.notifyDataSetChanged();
				listView.setSelection(chats.size()-1);
			}
		}
	}
}
