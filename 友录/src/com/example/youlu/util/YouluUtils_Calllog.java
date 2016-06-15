package com.example.youlu.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.CallLog;
import android.provider.CallLog.Calls;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.PhoneLookup;
import android.provider.ContactsContract.RawContacts;

import com.example.youlu.adapter.MyCalllogAdapter;
import com.example.youlu.adapter.MyNoteAdapter;
import com.example.youlu.adapter.NBCalllogAdapter;
import com.example.youlu.bean.Calllog;
import com.example.youlu.bean.Note;
import com.example.youlu.listener.OnLoadCalllogsFinshListener;

public class YouluUtils_Calllog {


	public static List<Calllog> getAllCalllogs(Context context){
		List<Calllog> calllogs = new ArrayList<Calllog>();

		ContentResolver cr1 = context.getContentResolver();
		Cursor cursor = cr1.query(CallLog.Calls.CONTENT_URI,new String[]{CallLog.Calls._ID,
				CallLog.Calls.TYPE,CallLog.Calls.CACHED_NAME,CallLog.Calls.NUMBER,CallLog.Calls.DATE,
				"photo_id"}, null, null, null);
		while(cursor.moveToNext()){
			Calllog calllog = new Calllog();
			calllog.set_id(cursor.getInt(0));
			calllog.setType(cursor.getInt(1));
			calllog.setName(cursor.getString(2));
			calllog.setNumber(cursor.getString(3));
			calllog.setTime(cursor.getLong(4));
			
			calllog.setPhoto_id(YouluUtils_Calllog.getPhotoIdByNumber(context,calllog.getNumber()));
			
			calllogs.add(calllog);
		}
		cursor.close();
		return calllogs;
	}

	public static void asyncGetAllCalllogs(final Context context,final OnLoadCalllogsFinshListener listener) {
		new AsyncTask<Void,Void,List<Calllog>>(){

			@Override
			protected List<Calllog> doInBackground(Void... params) {
				return getAllCalllogs(context);
			}
			@Override
			protected void onPostExecute(List<Calllog> list) {
				Collections.sort(list,new Comparator<Calllog>(){
					@Override
					public int compare(Calllog lhs, Calllog rhs) {
						return (lhs.getTime()-rhs.getTime())<0?1:-1;
					}
				});
				listener.onLoadFinish(list);
			}
		}.execute();
	}
	
	private static int getPhotoIdByNumber(Context context, String number) {
		int photoId = 0;
		ContentResolver cr = context.getContentResolver();
		Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, number);
		Cursor c = cr.query(uri,new String[]{PhoneLookup.PHOTO_ID},null, null, null);
		while(c.moveToNext()){
			photoId = c.getInt(0);
		}
		return photoId;
	}
	
	public static void deleteCalllog(final Context context, final NBCalllogAdapter adapter, final Calllog calllog) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setIcon(android.R.drawable.ic_menu_info_details);
		builder.setTitle("确认删除");
		builder.setMessage("您确实要删除"+calllog.getName()+"吗?");
		builder.setNegativeButton("再想想", null);
		builder.setPositiveButton("删之", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				//通话记录的具体逻辑
				ContentResolver cr = context.getContentResolver();
				cr.delete(CallLog.Calls.CONTENT_URI,
						Calls._ID + " = ?", 
						new String[]{String.valueOf(calllog.get_id())});
				adapter.remove(calllog);

			}
		});

		builder.create().show();
	}

	

}
