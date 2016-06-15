package com.example.youlu.ui;

import com.example.youlu.R;

import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BaseFragment extends Fragment{

	public static final int LEFT = 0;
	public static final int RIGHT= 1;
	static enum POSITION{LEFT,RIGHT}
	

	public void  setHeaderViewTitle(View headerVeiw,String title){
		TextView  headerTv = (TextView) headerVeiw.findViewById(R.id.tv_headerview_middle);

		if(TextUtils.isEmpty(title)){
			headerTv.setText("");
		}else{
			headerTv.setText(title);
		}
	}

	public void setHeaderViewImageLeft(View headerView,int source){
		ImageView headerIv = (ImageView) headerView.findViewById(R.id.iv_headerview_left);
		headerIv.setImageResource(source);
	}

	public void setHeaderViewImageRight(View headerView,int source){
		ImageView headerIv = (ImageView) headerView.findViewById(R.id.iv_headerview_right);
		headerIv.setImageResource(source);
	}

	public void setHeaderViewImage(View headerView,int source,int position, int invisible){
		ImageView imageView = null;
		if(position==LEFT){
			imageView = (ImageView) headerView.findViewById(R.id.iv_headerview_left);
			imageView.setVisibility(invisible);
		}else{
			imageView = (ImageView) headerView.findViewById(R.id.iv_headerview_right);
			imageView.setVisibility(invisible);
		}
		imageView.setImageResource(source);
		
	}
	
	public void setHeaderViewImage(View headerView,int source,POSITION position){
		ImageView imageView = null;
		if(position==POSITION.LEFT){
			imageView = (ImageView) headerView.findViewById(R.id.iv_headerview_left);
			imageView.setVisibility(View.VISIBLE);
		}else{
			imageView = (ImageView) headerView.findViewById(R.id.iv_headerview_right);
			imageView.setVisibility(View.VISIBLE);
		}
		imageView.setImageResource(source);
	}
}
