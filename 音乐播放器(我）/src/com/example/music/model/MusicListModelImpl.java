package com.example.music.model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.music.bean.Music;
import com.example.music.listener.OnLoadMusicFinishListener;
import com.example.music.util.JSonUtil;

public class MusicListModelImpl implements IMusicListModel{
	@Override
	public void getMusicList(final OnLoadMusicFinishListener listener) {

		new Thread(){
			@Override
			public void run() {
				try {
					Log.d("Tag", "Thread-->start");
//										HttpClient client = new DefaultHttpClient();
//										HttpGet get = new HttpGet("http://192.168.12.103:8080/MusicServer/loadMusics.jsp");
//										HttpResponse resp = client.execute(get);
//										HttpEntity entity = resp.getEntity();
//										String string = EntityUtils.toString(entity);
//										final List<Music> musics = JSonUtil.getListByJson(string);
//										new Handler(Looper.getMainLooper()).post(new Runnable() {
//											@Override
//											public void run() {
//												listener.OnLoadMusicFinish(musics);
//											}
//										});
					URL url = new URL("http://192.168.12.103:8080/MusicServer/loadMusics.jsp");
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setDoInput(true);
					connection.connect();
					InputStream is = connection.getInputStream();
					BufferedReader br = new BufferedReader(new InputStreamReader(is));
					String line=null;
					StringBuilder sb = new StringBuilder();
					while((line=br.readLine())!=null){
						sb.append(line);
					}
					br.close();
					final List<Music> musics = JSonUtil.getListByJson(sb.toString());
					new Handler(Looper.getMainLooper()).post(new Runnable() {

						@Override
						public void run() {
							listener.OnLoadMusicFinish(musics);
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}


	//	public static void LoadEmps2(final OnLoadAllEmpsFinishListener listener){
	//		new AsyncTask<Void, Void, List<Employee>>() {
	//
	//			@Override
	//			protected List<Employee> doInBackground(Void... params) {
	//
	//				try {
	//					URL url = new URL("http://192.168.12.215:8080/ems/listEmp");
	//					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	//					connection.setRequestMethod("GET");//设定访问方式
	//					connection.setDoInput(true);//可以接收服务器给我返回内容
	//					connection.connect();//建立连接
	//					InputStream is = connection.getInputStream();
	//					BufferedReader br = new BufferedReader(new InputStreamReader(is));
	//					StringBuilder sb = new StringBuilder();
	//					String line = null;
	//					while((line=br.readLine())!=null){
	//						sb.append(line);
	//					}
	//					is.close();
	//					return JSonUtil.getListByJson(sb.toString());
	//					
	//
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//				return null;
	//			}
	//			
	//			protected void onPostExecute(List<Employee> emps) {
	//				listener.onLoadAllEmpsFinish(emps);
	//			};
	//		}.execute();
	//	}

}
