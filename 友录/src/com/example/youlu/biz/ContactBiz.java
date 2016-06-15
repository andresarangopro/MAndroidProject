package com.example.youlu.biz;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.example.youlu.adapter.MyGridViewAdapter;
import com.example.youlu.bean.Contact;
import com.example.youlu.listener.OnLoadBitmapFinshListener;
import com.example.youlu.listener.OnLoadContactsFinshListener;
import com.example.youlu.util.YouluUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * 业务类
 * 关注所有项目与“联系人 ”相关的内容
 * @author pjy
 * 
 * */
public class ContactBiz {
	private Context context;

	public ContactBiz(Context context){
		this.context = context; 
	}
	/**
	 * 获取设备上所有联系人的信息
	 * */
	public List<Contact> getAllContacts(){
		List<Contact> list = YouluUtils.getAllContacts(context);
		Collections.sort(list,new Comparator<Contact>(){
			@Override
			public int compare(Contact lhs, Contact rhs) {
				return lhs.getName().toUpperCase().compareTo(rhs.getName().toUpperCase());
			}
		});
		Contact contact = new Contact();
		contact.setName("添加联系人");
		list.add(0, contact);
		return list;
	}

	public Bitmap getBitmap(int photoId){
		return YouluUtils.loadBitmap(context, photoId);
	}
	public void showDetail(Contact contact) {
		YouluUtils.showDetail(context, contact);
	}
	public void showDelete(Contact contact,MyGridViewAdapter adapter) {
		YouluUtils.showDelete(context,contact,adapter);
	}
	public void asyncGetAllContacts (MyGridViewAdapter adapter){
		YouluUtils.asyncGetAllContacts(context, adapter);
	}
	public void asyncGetAllContacts2 (OnLoadContactsFinshListener listener){
		YouluUtils.asyncGetAllContacts2(context, listener);
	}
	public void getBitmapByAsync(int photoId, OnLoadBitmapFinshListener listener) {
		YouluUtils.getBitmapByAsync(context,photoId, listener);
	}
	
}
