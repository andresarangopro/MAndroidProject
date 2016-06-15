package com.example.youlu.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.youlu.R;
import com.example.youlu.bean.Calllog;
import com.example.youlu.biz.CalllogBiz;
import com.example.youlu.biz.ContactBiz;
import com.example.youlu.listener.OnLoadBitmapFinshListener;


public class NBCalllogAdapter extends MyBaseAdapter<Calllog>{
	CalllogBiz biz;
	
	public NBCalllogAdapter(Context context, List<Calllog> datasource) {
		super(context, datasource);
		biz = new CalllogBiz(context);
	}

	@Override
	public View getItemView(int position, View convertView, ViewGroup parent) {
		View view =null;
		ViewHolder vh=null;
		if(convertView==null){//convertView (可重用的列表项对象)
			view = View.inflate(context, R.layout.item_listview_calllogfragment, null);
			vh=new ViewHolder();

			vh.logo=(ImageView) view.findViewById(R.id.iv_photo_calllogframent_list_item);
			vh.isCallOut=(ImageView) view.findViewById(R.id.is_callout);

			vh.time=(TextView) view.findViewById(R.id.tv_time_calllogframent_list_item);
			vh.name=(TextView) view.findViewById(R.id.tv_name_calllogframent_list_item);
			vh.number=(TextView) view.findViewById(R.id.tv_number_calllogframent_list_item);

			view.setTag(vh);
		}else{
			view=convertView;
			vh=(ViewHolder)convertView.getTag();
		}
		ImageView isCallout= (ImageView) view.findViewById(R.id.is_callout);
		Calllog calllog = datasource.get(position);


		vh.number.setText(calllog.getNumber());
		biz.setName(vh.name,calllog,isCallout);
		biz.setTime(vh.time,calllog.getTime());
		//		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd hh:mm");
		//		vh.time.setText(sdf.format(calllog.getTime()));	

		final ImageView logo = vh.logo;
		logo.setImageResource(R.drawable.ic_contact);
		final ImageView point = (ImageView) view.findViewById(R.id.iv_point_calllogframent_list_item);
		if(calllog.getName()==null){
			vh.name.setText("陌生人");
			point.setVisibility(ImageView.VISIBLE);
			logo.setImageResource(R.drawable.ic_contact);
		}else{
			new ContactBiz(context).getBitmapByAsync(calllog.getPhoto_id(),new OnLoadBitmapFinshListener() {
				@Override
				public void onLoadFinish(Bitmap bm) {
					logo.setImageBitmap(bm);
					point.setVisibility(ImageView.INVISIBLE);
				}
			});
		}

		return view;
	
	}
	class ViewHolder{
		ImageView logo;
		ImageView isCallOut;
		TextView name;
		TextView number;
		TextView time;
	}
	
}
