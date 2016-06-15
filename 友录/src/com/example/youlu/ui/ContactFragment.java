package com.example.youlu.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.youlu.R;
import com.example.youlu.adapter.MyGridViewAdapter;
import com.example.youlu.bean.Contact;
import com.example.youlu.biz.ContactBiz;
import com.example.youlu.listener.OnLoadContactsFinshListener;
import com.example.youlu.ui.BaseFragment.POSITION;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class ContactFragment extends BaseFragment {
	View headerView ;
	GridView gridView;
	List<Contact> contacts;
	ContactBiz biz;
	private MyGridViewAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.contact_fragment, container, false);
		biz = new ContactBiz(getActivity());
		initGridView(view);
		initTitleView(view);
		return view;
	}
	

	@Override
	public void onResume() {
		super.onResume();
		refresh();
	}
	/**
	 * 刷新数据表里的数据
	 * */
	private void refresh() {
//		adapter.addAll(biz.getAllContacts(), true);
//		biz.asyncGetAllContacts(adapter);
		biz.asyncGetAllContacts2(new OnLoadContactsFinshListener() {
			
			@Override
			public void onLoadFinish(List<Contact> contacts) {
				adapter.addAll(contacts, true);
			}
		});
	}

	private void initGridView(View view) {

		gridView = (GridView) view.findViewById(R.id.gv_contactFragment_contactContainer);
		contacts = new ArrayList();
		Log.d("TAG", contacts.toString());
		adapter = new MyGridViewAdapter(contacts, getActivity());
		gridView.setAdapter(adapter);

		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(position==0){
					Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
					intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
					intent.putExtra("finishActivityOnSaveCompleted", true);
					startActivity(intent);
				}else{
					Contact contact = adapter.getItem(position);
					biz.showDetail(contact);
				}
			}
		});
		gridView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(position>0){
					Contact contact = adapter.getItem(position);
					biz.showDelete(contact,adapter);
				}
				
				return false;
			}
		});
	}

	private void initTitleView(View view) {
	 	headerView = view.findViewById(R.id.title_contact_fragment);
	 	setHeaderViewTitle(headerView, "通讯录");
	 	setHeaderViewImage(headerView, R.drawable.ic_back,POSITION.LEFT);
	 	setHeaderViewImage(headerView, R.drawable.ic_search,POSITION.RIGHT);
	}
}

