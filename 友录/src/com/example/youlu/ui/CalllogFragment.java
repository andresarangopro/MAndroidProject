package com.example.youlu.ui;


import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.youlu.R;
import com.example.youlu.adapter.MyCalllogAdapter;
import com.example.youlu.bean.Calllog;
import com.example.youlu.biz.CalllogBiz;
import com.example.youlu.listener.OnLoadCalllogsFinshListener;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class CalllogFragment extends BaseFragment {
	
	TextView headerTv;
	View headerView ;
	ListView lv;
	List<Calllog> calllogs;
	CalllogBiz biz;
	private MyCalllogAdapter adapter;
	public CalllogFragment() {
		Log.i("TAG", "Fragment01()");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.calllog_fragment, container, false);
		biz = new CalllogBiz(getActivity());
		initListView(view);
		initTitleView(view);
		return view;
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
		lv = (ListView) view.findViewById(R.id.lv_calllogfragment_callscontainer);
		calllogs = new ArrayList();
		
		adapter = new MyCalllogAdapter(calllogs,getActivity());
		lv.setAdapter(adapter);
//		lv.setOnItemLongClickListener(new OnItemLongClickListener() {
//			@Override
//			public boolean onItemLongClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				Calllog calllog = (Calllog) adapter.getItem(position);
//				biz.deleteCalllog(adapter,calllog);
//				return false;
//			}
//		});
	}
	
	private void initTitleView(View view) {
	 	headerView = view.findViewById(R.id.title_calllog_fragment);
//	 	headerTv = (TextView) header.findViewById(R.id.tv_headerview_middle);
//	 	headerTv.setText("通话记录");
//	 	headerLeft = (ImageView) header.findViewById(R.id.iv_headerview_left);
//	 	headerLeft.setVisibility(View.INVISIBLE);
//	 	headerRight = (ImageView) header.findViewById(R.id.iv_headerview_right);
//	 	headerRight.setVisibility(View.INVISIBLE);
	 	setHeaderViewTitle(headerView, "通话记录");
//	 	setHeaderViewImage(headerView, R.drawable.ic_back,0,View.INVISIBLE);
//	 	setHeaderViewImage(headerView, R.drawable.ic_search,1,View.INVISIBLE);
	 	setHeaderViewImage(headerView, R.drawable.ic_back,POSITION.LEFT);
	 	setHeaderViewImage(headerView, R.drawable.ic_search,POSITION.RIGHT);
	}

}
