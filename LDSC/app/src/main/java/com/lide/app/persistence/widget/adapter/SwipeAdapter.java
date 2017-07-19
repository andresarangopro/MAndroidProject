package com.lide.app.persistence.widget.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lide.app.R;
import com.lide.app.bean.BeanToJson.ReceiveOrderToJsonForId;
import com.lide.app.persistence.widget.view.SwipeItemLayout;

import java.util.List;

public class SwipeAdapter extends BaseAdapter {
	private Context mContext = null;
	private LinearLayout order_id;
	private int count;

	private View view01,view02;
	private TextView orderAudit;
	private TextView orderDel;
	private List<ReceiveOrderToJsonForId> receiveGoodsOrders;
	private TextView order_name;
	private TextView order_address;

	public SwipeAdapter(Context context,List<ReceiveOrderToJsonForId> orders) {
		this.mContext = context;
		this.count = orders.size();
		this.receiveGoodsOrders = orders;
	}

	@Override
	public int getCount() {
		return count;
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View contentView, ViewGroup arg2) {
		ViewHolder holder = null;
		if(contentView==null){
			view01 = LayoutInflater.from(mContext).inflate(R.layout.item_order_layout, null);
			order_name = (TextView)view01.findViewById(R.id.order_name);
			order_address = (TextView)view01.findViewById(R.id.order_address);
			order_name.setText(receiveGoodsOrders.get(position).name);
			order_address.setText(receiveGoodsOrders.get(position).num);

			view02 = LayoutInflater.from(mContext).inflate(R.layout.item_order_item, null);
			orderAudit = (TextView)view02.findViewById(R.id.order_audit);
			orderDel = (TextView)view02.findViewById(R.id.order_del);
			contentView = new SwipeItemLayout(view01, view02, null, null);
			contentView.setTag(holder);
		}else{
			holder = (ViewHolder) contentView.getTag();
		}

		orderAudit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.w("SwipeAdapter","order_audit");
			}
		});
		orderDel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.w("SwipeAdapter","order_del");
			}
		});

		return contentView;
	}

	class ViewHolder{
		Button btn = null;
	}

}
