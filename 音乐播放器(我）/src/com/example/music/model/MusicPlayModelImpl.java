package com.example.music.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

public class MusicPlayModelImpl implements IMusicPlayModel{

	@Override
	public void download(final Context context,final String url, final String name) {
		// inputstream --以通知的形式告诉用户下载进度--> fileoutputstream
		new AsyncTask<Void, Integer, String>() {
			protected void onPreExecute() {
				//Log.d("TAG","开始下载");
				sendNotification(context, "开始下载", "开始下载");
			};
			
			@Override
			protected String doInBackground(Void... params) {
				File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),name+".mp3");
				try{
					URL u = new URL(url);
					HttpURLConnection connection = (HttpURLConnection) u.openConnection();
					connection.setRequestMethod("GET");
					connection.setDoInput(true);
					connection.connect();
					InputStream is = connection.getInputStream();
					FileOutputStream out = new FileOutputStream(file);
					int len = -1;
					
					int downloadCount = 0;//用来记录下载了多少字节
					int contentLength = connection.getContentLength();//要下载多少字节
					
					byte[] buff = new byte[4096];
					while((len=is.read(buff))!=-1){
						downloadCount += len;
						//把下载进度扔到onProgressUpdate方法中
						publishProgress((downloadCount*100)/contentLength);
						out.write(buff,0,len);
					}
					
					is.close();
					out.close();
					
					return file.getAbsolutePath();
				}catch(Exception e){
					e.printStackTrace();
				}
				return null;
			}
			protected void onProgressUpdate(Integer[] values) {
//				Log.d("TAG","已经下载了："+values[0]+"%");
				sendNotification(context, "下载中...", "已经下载了："+values[0]+"%");
			};
			protected void onPostExecute(String result) {
				if(result==null){
//					Log.d("TAG","下载失败了");
					sendNotification(context, "下载失败了","下载失败了");
				}else{
//					Log.d("TAG", "下载成功了，保存在"+result);
					sendNotification(context, "下载完毕！", "下载成功了，保存在"+result);
				}
			};
			

		}.execute();
	}
	
	private void sendNotification(Context context,String ticker,String text){
		NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification.Builder builder = new Notification.Builder(context);
		builder.setSmallIcon(android.R.drawable.ic_menu_info_details);
		builder.setTicker(ticker);
		builder.setContentTitle("下载");
		builder.setContentText(text);
		builder.setAutoCancel(true);
		Notification notification = builder.getNotification();
		manager.notify(0, notification );
	}
}
