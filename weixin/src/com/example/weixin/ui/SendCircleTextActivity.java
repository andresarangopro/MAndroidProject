package com.example.weixin.ui;

import com.example.weixin.CustomApplcation;
import com.example.weixin.R;
import com.example.weixin.manager.CircleManager;

import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SendCircleTextActivity extends AddFriendActivity{
	ImageView ivLeft,ivRight;
	TextView tvTitle,tv;
	EditText etContent;
	CircleManager cm;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_text);
		init();
		initHeaderView();
	}
	
	private void init() {
		cm = new CircleManager(this);
		etContent = (EditText) findViewById(R.id.et_sent_text_activity);
	}

	private void initHeaderView() {
		
		tv = (TextView) findViewById(R.id.tv_header);
		tv.setVisibility(View.GONE);
		
		ivLeft = (ImageView)findViewById(R.id.iv_header_left);
		ivLeft.setColorFilter(Color.WHITE, Mode.SRC_ATOP);

		ivLeft.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		ivRight = (ImageView)findViewById(R.id.iv_header_right);
		ivRight.setImageResource(R.drawable.mail);
		ivRight.setColorFilter(Color.WHITE, Mode.SRC_ATOP);
		//用来控制图表显示与否
		ivRight.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				cm.SendTextToCircle(userManager.getCurrentUserName(),etContent.getText().toString());
			}
		});
		
		tvTitle = (TextView) findViewById(R.id.tv_header_title);
		tvTitle.setText("发表文字");
	
	}
}
