package com.example.weixin.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import cn.bmob.im.bean.BmobChatUser;
import cn.bmob.im.db.BmobDB;

import com.example.weixin.CustomApplcation;
import com.example.weixin.R;
import com.example.weixin.adapter.MessageAdapter;
import com.example.weixin.bean.Message;
import com.example.weixin.listener.OnLoadMessageFinishListener;
import com.example.weixin.manager.CircleManager;
import com.example.weixin.util.CollectionUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class CircleActivity extends ActivityBase{

	View headerView;
	ImageView ivLeft,ivRight;
	TextView tvTitle;

	PullToRefreshListView ptrListView;
	ListView listView;
	MessageAdapter adapter;
	List<BmobChatUser> friends; 
	List<Message> messages;
	View realtimeWeatherContainer;
	
	CircleManager cm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_circle);
		init();
		initHeaderView();
		initListView();
	}


	@Override
	protected void onResume() {
		super.onResume();
		refresh();
	}
	
	private void init() {
		CustomApplcation.getInstance().setContactList(CollectionUtils.list2map(BmobDB.create(this).getContactList()));
		Map<String,BmobChatUser> users = CustomApplcation.getInstance().getContactList();
		friends = CollectionUtils.map2list(users);
		
		cm = new CircleManager(this);
	}
	
	private void initListView() {
		ptrListView = (PullToRefreshListView) findViewById(R.id.lv_main_weathers);
		listView = ptrListView.getRefreshableView();
		messages = new ArrayList<Message>();
		adapter = new MessageAdapter(this, messages,friends);

		realtimeWeatherContainer = getLayoutInflater().inflate(R.layout.view_circle_header, listView,false);
		listView.addHeaderView(realtimeWeatherContainer);
		listView.setAdapter(adapter);

		ptrListView.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
						DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
				refreshView.getLoadingLayoutProxy().setLoadingDrawable(getResources().getDrawable(R.drawable.progress));
				refresh();
			}
		});
	}

	private void initHeaderView() {
		headerView = findViewById(R.id.headerview_circle);
		ivLeft = (ImageView) headerView.findViewById(R.id.iv_header_left);
		ivLeft.setColorFilter(Color.WHITE, Mode.SRC_ATOP);

		ivLeft.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});


		ivRight = (ImageView) headerView.findViewById(R.id.iv_header_right);
		ivRight.setColorFilter(Color.WHITE, Mode.SRC_ATOP);
		//用来控制图表显示与否
		ivRight.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			}
		});

		ivRight.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				Intent intent = new Intent(CircleActivity.this,SendCircleTextActivity.class);
				startActivity(intent);
				return true;
			}
		});
		tvTitle = (TextView) findViewById(R.id.tv_header_title);
	}

	private void refresh() {
		cm.getMessages(friends, new OnLoadMessageFinishListener() {
			
			@Override
			public void OnLoadMessageFinish(List<Message> messges) {
				ShowToast(messages.size());
				adapter.addAll(messges);
			}
		});
	}



}
