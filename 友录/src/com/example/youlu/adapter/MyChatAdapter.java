package com.example.youlu.adapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.youlu.R;
import com.example.youlu.bean.Sms;
import com.example.youlu.biz.ContactBiz;
import com.example.youlu.listener.OnLoadBitmapFinshListener;
import com.example.youlu.util.YouluUtils;

public class MyChatAdapter extends BaseAdapter {
	Context context;
	List<Sms> smses;
	LayoutInflater inflater;

	private static final int LEFT = 0;
	private static final int RIGHT = 1;
	private static final long SPAN = 3*60*1000;//时间跨度3分钟

	long time ;
	int photo_id;

	public MyChatAdapter(Context context, List<Sms> smses,Intent intent) {
		photo_id = intent.getIntExtra("photo_id", 1);
		this.context = context;
		this.smses = smses;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return smses.size();
	}

	@Override
	public Sms getItem(int arg0) {
		return smses.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//		View view =null;
		//		ViewHolder vh=null;
		//		Sms sms= smses.get(position);
		//		if(convertView==null){//convertView (可重用的列表项对象)
		//			view = LayoutInflater.from(context).inflate(R.layout.item_listview_chat_activity,null);
		//			vh=new ViewHolder();
		//
		//			vh.left=(LinearLayout) view.findViewById(R.id.left_item_listview_chat);
		//			vh.right=(LinearLayout) view.findViewById(R.id.right_item_listview_chat);
		//			view.setTag(vh);
		//		}else{
		//			view=convertView;
		//			vh=(ViewHolder)convertView.getTag();
		//		}
		//		if(sms.getType()==1){
		//			vh.logo=(ImageView) vh.left.findViewById(R.id.iv_head_listview_chat);
		//			vh.time=(TextView) vh.left.findViewById(R.id.tv_time_listview_chat);
		//			vh.content=(TextView) vh.left.findViewById(R.id.tv_content_listview_chat);
		//			final ImageView logo = vh.logo;
		//			new ContactBiz(context).getBitmapByAsync(photo_id,new OnLoadBitmapFinshListener() {
		//				@Override
		//				public void onLoadFinish(Bitmap bm) {
		//					logo.setImageBitmap(bm);
		//				}
		//			});
		//			vh.content.setText(sms.getBody());
		//			setTime(vh.time,sms);
		//			vh.left.setVisibility(View.VISIBLE);
		//			vh.right.setVisibility(View.GONE);
		//		}else{
		//			vh.logo=(ImageView) vh.right.findViewById(R.id.iv_head_listview_chat);
		//			vh.time=(TextView) vh.right.findViewById(R.id.tv_time_listview_chat);
		//			vh.content=(TextView) vh.right.findViewById(R.id.tv_content_listview_chat);
		//			vh.content.setText(sms.getBody());
		//			setTime(vh.time,sms);
		//			vh.left.setVisibility(View.GONE);
		//			vh.right.setVisibility(View.VISIBLE);
		//		}
		//		vh.time.setVisibility(View.VISIBLE);

		final ViewHolder vh;

		if(convertView==null){

			if(getItemViewType(position)==LEFT){
				convertView = inflater.inflate(R.layout.left_item_listview_chat_activity, parent,false);
			}else{
				convertView = inflater.inflate(R.layout.right_item_listview_chat_activity,parent,false);
			}

			vh = new ViewHolder();
			vh.logo = (ImageView) convertView.findViewById(R.id.iv_head_listview_chat);
			vh.content = (TextView) convertView.findViewById(R.id.tv_content_listview_chat);
			vh.time = (TextView) convertView.findViewById(R.id.tv_time_listview_chat);

			convertView.setTag(vh);

		}else{
			vh = (ViewHolder) convertView.getTag();
		}

		Sms sms = getItem(position);
		//正文
		YouluUtils.setFonts(context, vh.content);
		vh.content.setText(sms.getBody());
		//头像
		if(getItemViewType(position)==LEFT){
			new ContactBiz(context).getBitmapByAsync(photo_id,new OnLoadBitmapFinshListener() {
				@Override
				public void onLoadFinish(Bitmap bm) {
					vh.logo.setImageBitmap(bm);
				}
			});
		}else{
			vh.logo.setImageResource(R.drawable.ic_launcher);
		}

		//时间

		if(position==0){
			time = sms.getTime();
			vh.time.setVisibility(View.VISIBLE);
			setTime(vh.time, sms);
		}else if(sms.getTime()-time>SPAN){
			vh.time.setVisibility(View.VISIBLE);
			setTime(vh.time, sms);
			time = sms.getTime();//修改“基准”时间！
		}else{
			vh.time.setVisibility(View.GONE);
		}

		return convertView;
	}
	public static void setTime(TextView timeView, Sms sms) {
		timeView.setText("");
		long current = System.currentTimeMillis();
		long time = sms.getTime();
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(new Date(current));
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Date(time));
		int day1 =cal1.get(Calendar.DAY_OF_YEAR);
		int day2 =cal2.get(Calendar.DAY_OF_YEAR);
		int num = day1-day2;
		switch (num) {
		case 0:
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
			timeView.setText(sdf.format(sms.getTime()));	
			break;

		default:
			SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd hh:mm");
			timeView.setText(sdf1.format(sms.getTime()));	
			break;
		}
	}

	@Override
	public int getViewTypeCount() {
		//ListView中一共要使用多少种条目视图
		return 2;
	}

	@Override
	public int getItemViewType(int position) {
		//告诉适配器，显示position位置的数据时，应该使用哪一种条目视图
		Sms sms = smses.get(position);
		if(sms.getType()==1){
			return LEFT;
		}else{
			return RIGHT;
		}
	}

	public void addAll(List<Sms> ss,boolean isClear){
		if(isClear){
			smses.clear();
		}
		smses.addAll(ss);
		this.notifyDataSetChanged();
	}
	
	class ViewHolder{
		TextView time;
		ImageView logo;
		TextView content;
		LinearLayout left;
		LinearLayout right;
	}
}
