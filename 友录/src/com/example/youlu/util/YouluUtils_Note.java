package com.example.youlu.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.CallLog.Calls;
import android.provider.ContactsContract;
import android.provider.ContactsContract.PhoneLookup;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.youlu.R;
import com.example.youlu.adapter.MyNoteAdapter;
import com.example.youlu.bean.Note;
import com.example.youlu.biz.ContactBiz;
import com.example.youlu.holder.NoteViewHolder;
import com.example.youlu.listener.OnLoadBitmapFinshListener;
import com.example.youlu.listener.OnLoadNotesFinshListener;


public class YouluUtils_Note {



	public static List<Note> getAllNotes(Context context){
		List<Note> notes = new ArrayList<Note>();

		ContentResolver cr1 = context.getContentResolver();
		Cursor cursor = cr1.query(Uri.parse("content://mms-sms/conversations"),new String[]{"thread_id",
			"read","address","body","date","type"}, null, null, null);
		while(cursor.moveToNext()){

			Note note = new Note();
			note.set_id(cursor.getInt(0));
			note.setRead(cursor.getInt(1));
			note.setNumber(cursor.getString(2));
			note.setBody(cursor.getString(3));
			note.setDate(cursor.getLong(4));
			note.setType(cursor.getInt(5));

			setPhotoIdAndName(context,note);

			notes.add(note);
		}
		cursor.close();
		return notes;
	}


	private static void setPhotoIdAndName(Context context, Note note) {
		ContentResolver cr = context.getContentResolver();
		Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, note.getNumber());
		Cursor c = cr.query(uri,new String[]{PhoneLookup.PHOTO_ID,PhoneLookup.DISPLAY_NAME},null, null, null);
		while(c.moveToNext()){
			note.setPhoto_id(c.getInt(0));
			note.setName(c.getString(1));
		}
	}

	public static void asyncGetAllNotes(final Context context,final OnLoadNotesFinshListener listener) {
		new AsyncTask<Void,Void,List<Note>>(){

			@Override
			protected List<Note> doInBackground(Void... params) {
				return getAllNotes(context);
			}
			@Override
			protected void onPostExecute(List<Note> list) {
				Collections.sort(list,new Comparator<Note>(){
					@Override
					public int compare(Note lhs, Note rhs) {
						return (lhs.getDate()-rhs.getDate())<0?1:-1;
					}
				});
				listener.onLoadFinish(list);
			}
		}.execute();
	}

	public static void init(Context context, NoteViewHolder vh, Note note,View view) {
		setName(vh.name,note);
		YouluUtils.setTime(vh.time,note.getDate());
		setBody(vh.content,note);
		setImage(vh.logo,context,vh,note,view);
	}

	private static void setImage(final ImageView logo,Context context, NoteViewHolder vh, Note note,View view) {
		final ImageView point = (ImageView) view.findViewById(R.id.iv_point_smsframent_list_item);

		logo.setImageResource(R.drawable.ic_contact);
		if(note.getName()==null){
			vh.name.setText("陌生人");
			point.setVisibility(ImageView.VISIBLE);
			logo.setImageResource(R.drawable.ic_contact);
		}else{
			new ContactBiz(context).getBitmapByAsync(note.getPhoto_id(),new OnLoadBitmapFinshListener() {
				@Override
				public void onLoadFinish(Bitmap bm) {
					logo.setImageBitmap(bm);
					point.setVisibility(ImageView.INVISIBLE);
				}
			});
		}
	}


	private static void setBody(TextView content, Note note) {
		content.setText(note.getBody());
	}

	private static void setName(TextView name, Note note) {
		name.setText(note.getName());
		switch (note.getRead()) {
		case 1:
			name.setTextColor(Color.BLACK);
			break;
		case 0:
			name.setTextColor(Color.RED);
			break;
		}
	}

	private static void setTime(TextView timeView, Note note) {
		timeView.setText("");
		long current = System.currentTimeMillis();
		long time = note.getDate();
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
			timeView.setText(sdf.format(note.getDate()));	
			break;
		case 1:
			timeView.setText("昨天");
			break;
		case 2:
			timeView.setText("前天");
			break;
		default:
			SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd hh:mm");
			timeView.setText(sdf1.format(note.getDate()));	
			break;
		}
	}

	public static void deleteNote(final Context context, final MyNoteAdapter adapter,
			final Note note) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setIcon(android.R.drawable.ic_menu_info_details);
		builder.setTitle("确认删除");
		builder.setMessage("您确实要删除"+note.getName()+"吗?");
		builder.setNegativeButton("再想想", null);
		builder.setPositiveButton("删之", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				//通话记录的具体逻辑
				ContentResolver cr = context.getContentResolver();
//				cr.delete(Uri.parse("content://mms-sms/conversations"),
				cr.delete(Uri.parse("content://mms-sms"),
						"thread_id"+" = ?", 
						new String[]{String.valueOf(note.get_id())});
				adapter.remove(note);
			}
		});
		builder.create().show();
	}


	public static void update(Context context, int get_id) {
		ContentResolver contentResolver = context.getContentResolver();
		Uri uri = Uri.parse("content://sms");
		ContentValues values= new ContentValues();
		values.put("read", 1);
		contentResolver.update(uri, values, "thread_id=?",new String[]{String.valueOf(get_id)});
	}
}
