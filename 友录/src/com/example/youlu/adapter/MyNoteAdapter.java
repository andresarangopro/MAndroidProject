package com.example.youlu.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.youlu.R;
import com.example.youlu.bean.Note;
import com.example.youlu.biz.NoteBiz;
import com.example.youlu.holder.NoteViewHolder;


public class MyNoteAdapter extends BaseAdapter {
	private List<Note> notes ;
	private Context context;
	NoteBiz noteBiz;

	public MyNoteAdapter(List<Note> notes, Context context) {
		this.context = context;
		this.notes = notes;
		noteBiz = new NoteBiz(context);
	}

	@Override
	public int getCount() {
		return notes.size();
	}

	@Override
	public Note getItem(int position) {
		return notes.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view =null;
		NoteViewHolder vh=null;
		if(convertView==null){//convertView (可重用的列表项对象)
			view = View.inflate(context, R.layout. item_listview_smsfragment, null);
			vh=new NoteViewHolder();
			
			vh.logo=(ImageView) view.findViewById(R.id.iv_photo_smsframent_list_item);
			vh.time=(TextView) view.findViewById(R.id.tv_time_smsframent_list_item);
			vh.name=(TextView) view.findViewById(R.id.tv_name_smsframent_list_item);
			vh.content=(TextView) view.findViewById(R.id.tv_content_smsframent_list_item);
			view.setTag(vh);
		}else{
			view=convertView;
			vh=(NoteViewHolder)convertView.getTag();
		}
		Note note = notes.get(position);
		
		noteBiz.init(vh,note,view);
		
		return view;
	}
	
	
	public void addAll(List<Note> ns,boolean isClear){
		if(isClear){
			notes.clear();
		}
		notes.addAll(ns);
		this.notifyDataSetChanged();
	}

	public void remove(Note note){
		notes.remove(note);
		notifyDataSetChanged();
	}

	
}
