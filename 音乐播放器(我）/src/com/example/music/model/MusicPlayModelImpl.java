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
		// inputstream --��֪ͨ����ʽ�����û����ؽ���--> fileoutputstream
		new AsyncTask<Void, Integer, String>() {
			protected void onPreExecute() {
				//Log.d("TAG","��ʼ����");
				sendNotification(context, "��ʼ����", "��ʼ����");
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
					
					int downloadCount = 0;//������¼�����˶����ֽ�
					int contentLength = connection.getContentLength();//Ҫ���ض����ֽ�
					
					byte[] buff = new byte[4096];
					while((len=is.read(buff))!=-1){
						downloadCount += len;
						//�����ؽ����ӵ�onProgressUpdate������
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
//				Log.d("TAG","�Ѿ������ˣ�"+values[0]+"%");
				sendNotification(context, "������...", "�Ѿ������ˣ�"+values[0]+"%");
			};
			protected void onPostExecute(String result) {
				if(result==null){
//					Log.d("TAG","����ʧ����");
					sendNotification(context, "����ʧ����","����ʧ����");
				}else{
//					Log.d("TAG", "���سɹ��ˣ�������"+result);
					sendNotification(context, "������ϣ�", "���سɹ��ˣ�������"+result);
				}
			};
			

		}.execute();
	}
	
	private void sendNotification(Context context,String ticker,String text){
		NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification.Builder builder = new Notification.Builder(context);
		builder.setSmallIcon(android.R.drawable.ic_menu_info_details);
		builder.setTicker(ticker);
		builder.setContentTitle("����");
		builder.setContentText(text);
		builder.setAutoCancel(true);
		Notification notification = builder.getNotification();
		manager.notify(0, notification );
	}
}
