package com.example.youlu.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.w3c.dom.Text;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.PhoneLookup;
import android.provider.ContactsContract.RawContacts;
import android.support.v4.util.LruCache;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.youlu.R;
import com.example.youlu.adapter.MyGridViewAdapter;
import com.example.youlu.bean.Calllog;
import com.example.youlu.bean.Contact;
import com.example.youlu.bean.Note;
import com.example.youlu.bean.Sms;
import com.example.youlu.listener.OnLoadBitmapFinshListener;
import com.example.youlu.listener.OnLoadCalllogsFinshListener;
import com.example.youlu.listener.OnLoadContactsFinshListener;
import com.example.youlu.listener.OnLoadSmsesFinshListener;

public class YouluUtils {

	private static int maxSize = (int) (Runtime.getRuntime().maxMemory()/8);
	//	public static Map<Integer,Bitmap> cache = new HashMap<Integer,Bitmap>();
	public static LruCache<Integer, Bitmap> cache = new LruCache<Integer, Bitmap>(maxSize){
		protected int sizeOf(Integer key, Bitmap value) {
			return value.getRowBytes()*value.getHeight();
		};
	};
	private static Typeface typeFace;

	public static Bitmap loadBitmap(Context context ,int photoId){
		//		ContentResolver cr=context.getContentResolver();
		//		String[] columns={Data.DATA15};
		//		Cursor c=cr.query(Data.CONTENT_URI, columns, Data._ID+"="+photoId, null, null);
		//		Bitmap bitmap=null;
		//		if(c.moveToNext()){
		//			byte[] bytes=c.getBlob(0);
		//			bitmap=BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
		//		}
		//		c.close();\
		Bitmap bitmap= cache.get(photoId);
		if(bitmap==null){
			if(photoId==0){
				//没有为联系人设置头像
				//手动指定一个头像
				//bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
				bitmap = getMyAvatar(context);
				cache.put(photoId, bitmap);
			}else{
				ContentResolver cr=context.getContentResolver();
				String[] columns={Data.DATA15};
				Cursor c=cr.query(Data.CONTENT_URI, columns, Data._ID+"="+photoId, null, null);
				if(c.moveToNext()){
					byte[] bytes=c.getBlob(0);
					//方形图
					Bitmap avatar=BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
					bitmap = getcircleAvatar(context,avatar);
					cache.put(photoId, bitmap);
				}
				c.close();
			}
		}
		return bitmap;
	}

	public static void getBitmapByAsync(final Context context,final int photoId,final OnLoadBitmapFinshListener listener) {
		new AsyncTask<Void,Void,Bitmap>(){

			@Override
			protected Bitmap doInBackground(Void... params) {
				return loadBitmap(context,photoId );
			}
			protected void onPostExecute(Bitmap bm) {
				listener.onLoadFinish(bm);
			};
		}.execute();	
	}

	private static Bitmap getcircleAvatar(Context context,Bitmap avatar) {

		Bitmap bitmap = Bitmap.createBitmap(avatar.getWidth(),avatar.getHeight(),Config.ARGB_8888);
		//画布 canvas
		Canvas canvas = new Canvas(bitmap);
		//画笔Paint 
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.BLACK);
		float radius = Math.min(avatar.getWidth(), avatar.getHeight())/2;
		canvas.drawCircle(avatar.getWidth()/2,avatar.getHeight()/2 , radius, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(avatar, 0,0, paint);

		paint.setColor(Color.WHITE);
		paint.setStyle(Paint.Style.STROKE);
		float strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, context.getResources().getDisplayMetrics());
		paint.setStrokeWidth(strokeWidth);
		canvas.drawCircle(avatar.getWidth()/2, avatar.getHeight()/2 , radius-strokeWidth/2, paint);
		return bitmap;
	}

	public static List<Contact> getAllContacts(Context context){
		List<Contact> contacts = new ArrayList<Contact>();

		ContentResolver cr1 = context.getContentResolver();
		Cursor cursor = cr1.query(Contacts.CONTENT_URI,new String[]{Contacts._ID,Contacts.PHOTO_ID,Contacts.LOOKUP_KEY}, null, null, null);
		while(cursor.moveToNext()){
			Contact contact = new Contact();
			contact.setId(cursor.getInt(0));
			contact.setPhoto_id(cursor.getInt(1));
			contact.setLookupKey(cursor.getString(2));
			ContentResolver cr2 = context.getContentResolver();
			Cursor c = cr2.query(Data.CONTENT_URI,new String[]{Data.MIMETYPE,Data.DATA1},Data.RAW_CONTACT_ID+"=?",
					new String[]{String.valueOf(contact.getId())}, null);
			while(c.moveToNext()){
				String mimeType = c.getString(0);
				if(mimeType.equals("vnd.android.cursor.item/email_v2")){
					contact.setEmail(c.getString(1));
				}else if(mimeType.equals("vnd.android.cursor.item/organization")){
					contact.setCompany(c.getString(1));
				}else if(mimeType.equals("vnd.android.cursor.item/phone_v2")){
					contact.setNumber(c.getString(1));
				}else if(mimeType.equals("vnd.android.cursor.item/name")){
					contact.setName(c.getString(1));
				}else if(mimeType.equals("vnd.android.cursor.item/postal-address_v2")){
					contact.setAddress(c.getString(1));
				}
			}
			c.close();
			contacts.add(contact);

		}
		cursor.close();
		return contacts;
	}

	public static void asyncGetAllContacts(final Context context,final MyGridViewAdapter adapter){
		new AsyncTask<Void,Void,List<Contact>>(){

			@Override
			protected List<Contact> doInBackground(Void... params) {
				return getAllContacts(context);
			}
			@Override
			protected void onPostExecute(List<Contact> list) {
				Collections.sort(list,new Comparator<Contact>(){
					@Override
					public int compare(Contact lhs, Contact rhs) {
						return lhs.getName().toUpperCase().compareTo(rhs.getName().toUpperCase());
					}
				});
				Contact contact = new Contact();
				contact.setName("添加联系人");
				list.add(0, contact);
				adapter.addAll(list,true);
			}
		}.execute();
	}

	public static void asyncGetAllContacts2(final Context context,final OnLoadContactsFinshListener listener){
		new AsyncTask<Void,Void,List<Contact>>(){

			@Override
			protected List<Contact> doInBackground(Void... params) {
				return getAllContacts(context);
			}
			@Override
			protected void onPostExecute(List<Contact> list) {
				Collections.sort(list,new Comparator<Contact>(){
					@Override
					public int compare(Contact lhs, Contact rhs) {
						return lhs.getName().toUpperCase().compareTo(rhs.getName().toUpperCase());
					}
				});
				Contact contact = new Contact();
				contact.setName("添加联系人");
				list.add(0, contact);
				listener.onLoadFinish(list);
			}
		}.execute();
	}
	/**
	 * 手动绘制一个头像出来
	 * 深灰色背景，前景白色文字“友录”
	 * */
	private static Bitmap getMyAvatar(Context context){
		//绘制图像需要两个对象
		Bitmap bitmap = Bitmap.createBitmap(150, 150, Config.ARGB_8888);
		//画布 canvas
		Canvas canvas = new Canvas(bitmap);
		//画笔Paint 
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.DKGRAY);
		paint.setStyle(Style.FILL);
		canvas.drawCircle(75, 75, 75, paint);

		paint.setColor(Color.WHITE);
		int sp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, context.getResources().getDisplayMetrics());
		paint.setTextSize(sp);
		Rect bounds = new Rect();
		paint.getTextBounds("友录", 0, 2, bounds);
		float x = 75 - bounds.width()/2;
		float y = 75 + bounds.height()/2;

		//画白边
		paint.setStyle(Paint.Style.STROKE);
		float strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, context.getResources().getDisplayMetrics());
		paint.setStrokeWidth(strokeWidth);
		canvas.drawCircle(75, 75, 75-strokeWidth/2, paint);

		canvas.drawText("友录", x, y, paint);

		return bitmap;
	}

	public static void showDetail(final Context context, final Contact contact) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		//标准的AlertDialog.Builder的写法
		//buidler.setIcon/setTitle/setMessage/setPositiveButton/setNegativieButton
		//buidler.create().show();
		final AlertDialog dialog = builder.create();
		dialog.show();
		Window window = dialog.getWindow();

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//
		View view = inflater.inflate(R.layout.detail_layout, null);
		window.setContentView(view);

		ImageView ivAvatar = (ImageView) view.findViewById(R.id.iv_detail_avatar);
		TextView tvName = (TextView) view.findViewById(R.id.tv_detail_name);
		TextView tvNumber = (TextView) view.findViewById(R.id.tv_detail_number);

		ivAvatar.setImageBitmap(loadBitmap(context, contact.getPhoto_id()));
		tvName.setText(contact.getName());
		tvNumber.setText(contact.getNumber());

		ImageView ivEdit = (ImageView) view.findViewById(R.id.iv_detail_edit);
		ImageView ivClose = (ImageView) view.findViewById(R.id.iv_detail_close);

		ivClose.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		ivEdit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//发送一个隐式intent，打开系统的联系人界面编辑contact的信息
				removeFromCache(contact.getPhoto_id());
				Intent intent = new Intent(Intent.ACTION_EDIT);
				Uri data = ContactsContract.Contacts.getLookupUri(contact.getId(), contact.getLookupKey());
				intent.setDataAndType(data , ContactsContract.Contacts.CONTENT_ITEM_TYPE);
				intent.putExtra("finishActivityOnSaveCompleted", true);
				context.startActivity(intent);
				dialog.dismiss();
			}
		}); 

	}
	/*
	 *弹出一个对话框，询问用户是否删除contact
	 * **/
	public static void showDelete(final Context context, final Contact contact,final MyGridViewAdapter adapter) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setIcon(android.R.drawable.ic_menu_info_details);
		builder.setTitle("确认删除");
		builder.setMessage("您确实要删除"+contact.getName()+"吗?");
		builder.setNegativeButton("再想想", null);
		builder.setPositiveButton("删之", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				//删除联系人的具体逻辑
				//删除不能直接删除contacts表中该联系人的内容
				//而是去删除raw_contacts和data表中该联系人的内容
				ContentResolver cr = context.getContentResolver();
				//从raw_contacts表中删除内容
				cr.delete(ContactsContract.RawContacts.CONTENT_URI,
						RawContacts.CONTACT_ID + " = ?", 
						new String[]{String.valueOf(contact.getId())});
				//从data表中删除内容
				cr.delete(ContactsContract.Data.CONTENT_URI, 
						Data.CONTACT_ID + " = ?", 
						new String[]{String.valueOf(contact.getId())});
				adapter.remove(contact);

			}
		});

		builder.create().show();
	}

	/*
	 * 从缓存中删除photo指定的bitmap
	 * **/
	public static void removeFromCache(int photo){
		cache.remove(photo);
	}

	public static void setName(TextView name, Calllog calllog,ImageView isCallout) {
		name.setText(calllog.getName());
		isCallout.setVisibility(View.VISIBLE);
		isCallout.setImageResource(R.drawable.ic_outgoing_call);
		switch (calllog.getType()) {
		case 3:
			name.setTextColor(Color.RED);
			break;
		case 1:
			name.setTextColor(Color.BLACK);
		default:
			isCallout.setVisibility(View.INVISIBLE);
			name.setTextColor(Color.BLACK);
			break;
		}
	}

	public static void setTime(TextView timeView, Long time1) {
		timeView.setText("");
		long current = System.currentTimeMillis();
		long time =time1;
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
			timeView.setText(sdf.format(time1));	
			break;
		case 1:
			timeView.setText("昨天");
			break;
		case 2:
			timeView.setText("前天");
			break;
		default:
			SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd hh:mm");
			timeView.setText(sdf1.format(time1));	
			break;
		}
	}

	public static void asyncGetAllSmses(final Context context,final OnLoadSmsesFinshListener listener,final int thread_id) {
		new AsyncTask<Void,Void,List<Sms>>(){

			@Override
			protected List<Sms> doInBackground(Void... params) {
				return getAllSmses(context,thread_id);
			}
			@Override
			protected void onPostExecute(List<Sms> list) {
				Collections.sort(list,new Comparator<Sms>(){
					@Override
					public int compare(Sms lhs, Sms rhs) {
						return (lhs.getTime()-rhs.getTime())>0?1:-1;
					}
				});
				listener.onLoadFinish(list);
			}
		}.execute();
	}

	public static List<Sms> getAllSmses(Context context,int thread_id){
		List<Sms> smses = new ArrayList<Sms>();

		ContentResolver cr1 = context.getContentResolver();
		Cursor cursor = cr1.query(Uri.parse("content://sms/"),new String[]{"_id",
			"body","date","type"},"thread_id"+" = ?" ,new String[]{String.valueOf(thread_id)}, null);
		while(cursor.moveToNext()){
			Sms sms = new Sms();
			sms.set_id(cursor.getInt(0));
			sms.setBody(cursor.getString(1));
			sms.setTime(cursor.getLong(2));
			sms.setType(cursor.getInt(3));
			smses.add(sms);
		}
		cursor.close();
		return smses;
	}

	public static void sendSms(Context context, String number, String content) {
		SmsManager default1 = SmsManager.getDefault();
		Intent intent = new Intent("com.tarena.SENT");
		intent.putExtra("date", System.currentTimeMillis());
		intent.putExtra("body", content);
		PendingIntent pi1 = PendingIntent.getBroadcast(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
		default1.sendTextMessage(number, 
				null, 
				content,
				pi1, 
				null);
	}

	public static void saveSendSms(Context context, long date, String body,
			String number, int thread_id) {
		ContentResolver cr = context.getContentResolver();
		ContentValues values = new ContentValues();
		//		列名:thread_id,值为：3 *可以不查了
		//		列名:address,值为：13001301300 *可以不查了
		//		列名:person,值为：1 *可以不查了
		//		列名:date,值为：1462352906697
		//		列名:type,值为：1 收到的 2 发出的
		//		列名:body,值为：hello
		values.put("thread_id", thread_id);
		values.put("address", number);
		values.put("person", "");
		values.put("date", date);
		values.put("type", 2);
		values.put("body", body);
		cr.insert(Uri.parse("content://sms/sent"), values);

	}

	public static void receiverSms(Context context, Bundle bundle, int thread_id) {
		Object[] pdus = (Object[]) bundle.get("pdus");
		StringBuilder sb = new StringBuilder();
		String number = "";
		//把一个一个pdus转化为一段一段的短消息
		for(int i=0;i<pdus.length;i++){
			SmsMessage message = SmsMessage.createFromPdu((byte[]) pdus[i]);
			sb.append(message.getDisplayMessageBody());
			number = message.getDisplayOriginatingAddress();
			saveReceiverSms(context,thread_id,number,sb.toString(),thread_id);
		}

		Log.d("TAG", "发送方电话："+number+"，发送的内容："+sb);
	}

	public static void saveReceiverSms(Context context, long date, String body,
			String number, int thread_id) {
		ContentResolver cr = context.getContentResolver();
		ContentValues values = new ContentValues();
		//		列名:thread_id,值为：3 *可以不查了
		//		列名:address,值为：13001301300 *可以不查了
		//		列名:person,值为：1 *可以不查了
		//		列名:date,值为：1462352906697
		//		列名:type,值为：1 收到的 2 发出的
		//		列名:body,值为：hello
		values.put("thread_id", thread_id);
		values.put("address", number);
//		int id = getContactIdByNumber(context,number);
//		if(id==-1){
//			values.put("person","");
//		}else{
//			values.put("person",id);
//		}
		values.put("date", date);
		values.put("type", 1);
		values.put("body", body);
		cr.insert(Uri.parse("content://sms/inbox"), values);

	}

	private static int getContactIdByNumber(Context context, String number) {
		int id = -1;
		ContentResolver cr = context.getContentResolver();
		Uri uri= Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI, number);
		Cursor cursor= cr.query(uri,new String[]{"name_raw_contact_id"},null ,null, null);
		if(cursor.moveToNext()){
			id = cursor.getInt(0);
		}
		return id;
	}
	
	public static String getReceiveNumber(Context context,Bundle bundle){
		Object[] pdus = (Object[]) bundle.get("pdus");
		StringBuilder sb = new StringBuilder();
		String number = "";
		for(int i=0;i<pdus.length;i++){
			SmsMessage message = SmsMessage.createFromPdu((byte[]) pdus[i]);
			number = message.getDisplayOriginatingAddress();
		}
		return number.toString();
	}
	
	public static void setFonts(Context context,TextView tv){
		if(typeFace ==null){
//		1）将字体文件读入内存中，变成一个字体文件
		AssetManager mgr = context.getAssets();
//		注意路径的大小写和扩展名
		String path = "fonts/3T.TTF";
		typeFace= Typeface.createFromAsset(mgr, path);
//		2) 使用字体文件对象
		tv.setTypeface(typeFace);
		}
		tv.setTypeface(typeFace);
	}
}
