package com.example.youlu.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.example.youlu.R;
import com.example.youlu.adapter.MyNoteAdapter;
import com.example.youlu.bean.Calllog;
import com.example.youlu.bean.Contact;
import com.example.youlu.bean.Note;
import com.example.youlu.bean.Sms;
import com.example.youlu.biz.NoteBiz;
import com.example.youlu.listener.OnLoadNotesFinshListener;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class SmsFragment extends BaseFragment {
	View headerView;
	ListView lv;
	List<Note> notes;
	NoteBiz biz;
	
	int thread_id;
	String name;
	int photo_id;
	String number;
	private MyNoteAdapter adapter;
	public SmsFragment(){
		Log.i("TAG", "Fragment01()");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.sms_fragment, container, false);
		biz = new NoteBiz(getActivity());
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
		biz.asyncGetAllNotes(new OnLoadNotesFinshListener() {
			@Override
			public void onLoadFinish(List<Note> Notes) {
				Log.d("TAG", Notes.toString());
				adapter.addAll(Notes, true);
			}
		});
	}

	
	private void initListView(View view) {
		lv = (ListView) view.findViewById(R.id.lv_smsfragment_smscontainer);
		notes = new ArrayList();
		
		adapter = new MyNoteAdapter(notes,getActivity());
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id) {
				Intent intent = new Intent(getActivity(),ChatActivity.class);
				Note note = adapter.getItem(position);
				intent.putExtra("thread_id",note.get_id());
				String name = note.getName();
				if (TextUtils.isEmpty(name)) {
					intent.putExtra("name", note.getNumber());
				}else{
					intent.putExtra("name", note.getName());
				}
				intent.putExtra("photo_id", note.getPhoto_id());
				intent.putExtra("number", note.getNumber());
				biz.Update(note.get_id());
				startActivity(intent);
			}
		});
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				Note note = adapter.getItem(position);
				biz.deleteNote(adapter,note);
				return false;
			}
		});
	}
	
	private void initTitleView(View view) {
	 	headerView = view.findViewById(R.id.title_sms_fragment);
	 	
	 	setHeaderViewTitle(headerView, "¶ÌÐÅ");
	}

}
