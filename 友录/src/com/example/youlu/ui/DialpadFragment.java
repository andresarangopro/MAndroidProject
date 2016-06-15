package com.example.youlu.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.youlu.R;
import com.example.youlu.adapter.NBCalllogAdapter;
import com.example.youlu.bean.Calllog;
import com.example.youlu.biz.CalllogBiz;
import com.example.youlu.listener.OnLoadCalllogsFinshListener;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class DialpadFragment extends BaseFragment {

	private static final String[] keys = {"1","2","3","4","5","6","7","8","9","0","*","#"};

	TextView headerTv;
	ImageView headerRight;
	View headerView ;
	ListView lv;
	List<Calllog> calllogs;
	CalllogBiz biz;
	private NBCalllogAdapter adapter;
	MediaPlayer mediaPlayer;

	Button call;
	RelativeLayout dialPad;//拨号键盘
	public DialpadFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.dialgad_fragment, container, false);
		biz = new CalllogBiz(getActivity());

		initListView(view);
		initTitleView(view);
		initDialPad(view);
		initMediaPlayer();
		return view;
	}

	private void initMediaPlayer() {
		mediaPlayer = MediaPlayer.create(getActivity(), R.raw.a);
		
	}

	@Override
	public void onResume() {
		super.onResume();
		refresh();
	}
	private void refresh() {
		biz.asyncGetAllCalllogs(new OnLoadCalllogsFinshListener() {
			@Override
			public void onLoadFinish(List<Calllog> calllogs) {
				Log.d("TAG", calllogs.toString());
				adapter.addAll(calllogs, true);
			}
		});
	}

	private void initListView(View view) {
		lv = (ListView) view.findViewById(R.id.lv_dial_calllogs);
		calllogs = new ArrayList<Calllog>();

		adapter = new NBCalllogAdapter(getActivity(),calllogs);
		lv.setAdapter(adapter);
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				Calllog calllog = (Calllog) adapter.getItem(position);
				biz.deleteCalllog(adapter,calllog);
				return false;
			}
		});
	}
	private void initDialPad(View view) {
		dialPad = (RelativeLayout) view.findViewById(R.id.rl_dial_container);
		//创建12个TextView
		DisplayMetrics outMetrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
		int width = outMetrics.widthPixels/3;
		int height = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 
				50, 
				getActivity().getResources().getDisplayMetrics());

		for(int i=0;i<12;i++){
			TextView tv = new TextView(getActivity());
			tv.setId(i+1);//安卓中没有为0的id
			tv.setText(keys[i]);
			tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,30);
			tv.setGravity(Gravity.CENTER);
			//设定tv的宽度和高度
			//tv在一个容器中，不能自己设定自己的宽度高度
			//tv.setWidth(pixels)/tv.setHeight(px);
			//利用params指定每一个tv的宽度和高度
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
			//指定每一个tv的相对位置
			if(i%3!=0){
				//要添加“右侧”规则
				params.addRule(RelativeLayout.RIGHT_OF, i);
			}
			if(i>=3){
				//要添加下方规则
				params.addRule(RelativeLayout.BELOW, i-2);
			}
			tv.setLayoutParams(params);
			tv.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					TextView textview  = (TextView) v;
					String number = textview.getText().toString();
					String title= headerTv.getText().toString();
					if(title.length()>=13)return;
					if("拨打电话".equals(title)){
						headerTv.setText(number);
					}else if(title.length()==3||title.length()==8){
						headerTv.append("-");
						headerTv.append(number);
					}else {
						headerTv.append(number);
					}
				}
			});
			dialPad.addView(tv);
		}

//		call = (Button) view.findViewById(R.id.iv_dial_call);
//		call.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				mediaPlayer.start();
//				String number = headerTv.getText().toString();
//				if("拨打电话".equals(number)){
//					return;
//				}
//				Intent intent = new Intent();
//				Uri parse = Uri.parse("tel:"+number);
//				intent.setData(parse);
//				startActivity(intent);
//			}
//		});

	}
	private void initTitleView(View view) {
		headerView = view.findViewById(R.id.headerview);
		headerTv = (TextView) headerView.findViewById(R.id.tv_headerview_middle);
		//	 	headerTv.setText("通话记录");
		//	 	headerLeft = (ImageView) header.findViewById(R.id.iv_headerview_left);
		//	 	headerLeft.setVisibility(View.INVISIBLE);
		headerRight = (ImageView) headerView.findViewById(R.id.iv_headerview_right);
		headerRight.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String title= headerTv.getText().toString();
				if("拨打电话".equals(title)){
					return;
				}
				if(title.length()==1){
					headerTv.setText("拨打电话");
					return;
				}
				  
				if(title.length()==5||title.length()==10){
					headerTv.setText(title.substring(0,title.length()-2));
				}else {
					headerTv.setText(title.substring(0,title.length()-1));
				}
			}
		});
		//	 	headerRight.setVisibility(View.INVISIBLE);
		setHeaderViewTitle(headerView, "拨打电话");
		//	 	setHeaderViewImage(headerView, R.drawable.ic_back,0,View.INVISIBLE);
		//	 	setHeaderViewImage(headerView, R.drawable.ic_search,1,View.INVISIBLE);
		setHeaderViewImage(headerView, R.drawable.ic_back,POSITION.LEFT);
		setHeaderViewImage(headerView, R.drawable.ic_backspace,POSITION.RIGHT);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mediaPlayer.release();
	}
}
