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
 * ҵ����
 * ��ע������Ŀ�롰��ϵ�� ����ص�����
 * @author pjy
 * 
 * */
public class ContactBiz {
	private Context context;

	public ContactBiz(Context context){
		this.context = context; 
	}
	/**
	 * ��ȡ�豸��������ϵ�˵���Ϣ
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
		contact.setName("�����ϵ��");
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
