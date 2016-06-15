package com.example.youlu.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.youlu.R;
import com.example.youlu.bean.Contact;
import com.example.youlu.biz.ContactBiz;
import com.example.youlu.listener.OnLoadBitmapFinshListener;

public class MyGridViewAdapter extends BaseAdapter {

	private List<Contact> contacts;
	private Context context;
	ContactBiz contactBiz;

	public MyGridViewAdapter(List<Contact> contacts,Context context){
		this.contacts = contacts; 
		this.context = context;
		contactBiz = new ContactBiz(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return contacts.size();
	}

	@Override
	public Contact getItem(int position) {
		// TODO Auto-generated method stub
		return contacts.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view =null;
		ViewHolder vh=null;
		if(convertView==null){//convertView (可重用的列表项对象)
			view = View.inflate(context, R.layout.item_gridview_contactfragment, null);
			vh=new ViewHolder();
			vh.logo=(ImageView) view.findViewById(R.id.logo);
			vh.name=(TextView) view.findViewById(R.id.name);
			view.setTag(vh);
		}else{
			view=convertView;
			vh=(ViewHolder)convertView.getTag();
		}
		Contact contact = contacts.get(position);
		
		if(position==0){
			vh.logo.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_add_contact));
		}else{
//			vh.logo.setImageBitmap(contactBiz.getBitmap(contact.getPhoto_id()));
			final ImageView logo = vh.logo;
			logo.setImageResource(R.drawable.aaa);
			contactBiz.getBitmapByAsync(contact.getPhoto_id(),new OnLoadBitmapFinshListener() {
				@Override
				public void onLoadFinish(Bitmap bm) {
					logo.setImageBitmap(bm);
				}
			});
		}
		vh.name.setText(contact.getName());
		 
		return view;
	}
	
	public void addAll(List<Contact> list,boolean isClear){
		if(isClear){
			contacts.clear();
		}
		contacts.addAll(list);
		this.notifyDataSetChanged();
	}
	
	public void remove(Contact contact){
		contacts.remove(contact);
		notifyDataSetChanged();
	}
	
	class ViewHolder{
		ImageView logo;
		TextView name;
	}
}