package com.example.music.activity;


import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.activity.R;

public class MyBaseActvity extends Activity{

	public static final int LEFT = 0;
	public static final int RIGHT= 1;
	public static enum Position{LEFT,RIGHT}
	

	public void  setHeaderViewTitle(View headerVeiw,String title){
		TextView  headerTv = (TextView) headerVeiw.findViewById(R.id.tv_header_view);

		if(TextUtils.isEmpty(title)){
			headerTv.setText("");
		}else{
			headerTv.setText(title);
		}
	}

	public ImageView setHeaderViewImage(View headerView,int source,Position position){
		ImageView imageView = null;
		if(position==Position.LEFT){
			imageView = (ImageView) headerView.findViewById(R.id.iv_header_view_left);
			imageView.setVisibility(View.VISIBLE);
		}else{
			imageView = (ImageView) headerView.findViewById(R.id.iv_header_view_right);
			imageView.setVisibility(View.VISIBLE);
		}
		imageView.setImageResource(source);
		imageView.setColorFilter(Color.WHITE, Mode.SRC_ATOP);

		return imageView;
	}
}
