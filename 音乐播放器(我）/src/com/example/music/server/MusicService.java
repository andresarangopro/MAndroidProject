package com.example.music.server;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.IBinder;
import android.util.Log;

import com.example.music.constant.Constant;


public class MusicService extends Service {
	//���ֲ�����
	MediaPlayer mediaPlayer;
	//���²��Ž��ȵ��߳�
	Thread updateProgressThread;
	//����"��ͣ"��ʱ�����ֲ��ŵ���ʱ��
	//�����¿�ʼ���ŵ�ʱ��Ӧ�ô���ͣʱλ�ò���
	int seekTime;
	//���ֲ������Ƿ���paused״̬
	//���������ֹ�����(mediaPlayer����started״̬)
	//����mediaPlayer��pause����������mediaPlayer����paused״̬
	boolean isPaused;
	//��������PlayActivity���͹����Ĳ��š���ͣ��������ֹ㲥
	MyReceiver receiver;
	
	String pauseUrl;//�������水�¡���ͣ����ʱ��������URL
	@Override
	public void onCreate() {
		super.onCreate();
		//�Ը������Խ��г�ʼ��
		//1)mediaPlayer�ĸ�ֵ
		mediaPlayer = new MediaPlayer();
		//2)mediaPlayer��������������
		mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
			
			@Override
			public void onPrepared(MediaPlayer mp) {
				mp.start();
				//mediaPlayer.start();
				
			}
		});
		//3)mediaPlayer��ǰ����������ϼ����������
		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				mp.stop();
				//��PlayActivity���͹㲥��������һ�׸���
				Intent intent = new Intent(Constant.ACTION_UPDATE_MUSIC_PLAYING);
				sendBroadcast(intent);
			}
		});
		//4)���½����̵߳Ĵ���������
		updateProgressThread = new Thread(){
			public void run() {
				try {
					while(!Thread.interrupted()){
						if(mediaPlayer.isPlaying()){
							//���mediaPlayer���ڲ������ֵ�״̬
							//���͹㲥������PlayActivity��sbProgress
							Intent intent = new Intent(Constant.ACTION_UPDATE_PROGRESS);
							//������ǰ���Ž���
							int currentPosition = mediaPlayer.getCurrentPosition();
							//��ǰ���Ÿ������ܳ���
							int duration = mediaPlayer.getDuration();
							intent.putExtra("current", currentPosition);
							intent.putExtra("duration", duration);
							sendBroadcast(intent);
						}
						Thread.sleep(800);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
		};
		
		updateProgressThread.start();
		
		//5)�㲥�������Ĵ�����ע��
		receiver = new MyReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Constant.ACTION_PLAY_MUSIC);
		filter.addAction(Constant.ACTION_PAUSE_MUSIC);
		filter.addAction(Constant.ACTION_SEEKTO_MUSIC);
		registerReceiver(receiver, filter );
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		//�޸�Service��������ʽΪ��ճ��
		return Service.START_NOT_STICKY;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		//�Ը������Խ���"����"
		//1)ע���㲥������
		unregisterReceiver(receiver);
		//2)�ͷ�mediaPlayer
		mediaPlayer.release();
		//3)�������ȸ����߳�
		//Thread��������һ������boolean flag
		//����interrupt���������ø����Դ�false-->true
		//ͬʱ�������ǰThread�����Ǵ��ڿ��жϵ�����״̬ʱ(sleep��)
		//���׳�һ��InterruptException�쳣
		//ֻҪThread.interrupted()��⵽ture����
		//�׳������InterruptException�쳣������
		//flag���ԾͿ��Դ�ture�ص�false
		updateProgressThread.interrupt();
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	/**
	 * MusicServie��������ָ��λ�����ֵķ���
	 * @param url
	 */
	private void play(String url){
		try {
			if(isPaused && url.equals(pauseUrl)){
				//����ͣ״̬��ʼ�������Ÿ���
				mediaPlayer.start();//paused--->started
				mediaPlayer.seekTo(seekTime);//�ƶ�����ͣʱ��λ��
				isPaused = false;
				seekTime = 0;
			}else{
				isPaused = false;
				seekTime = 0;
				//ȫ�²���һ�׸���
				mediaPlayer.reset();
				mediaPlayer.setDataSource(url);
				mediaPlayer.prepareAsync();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * MusicService������ͣ���ֲ��ŵķ���
	 */
	private void pause(){
		isPaused = true;
		seekTime = mediaPlayer.getCurrentPosition();
		mediaPlayer.pause();
	}
	
	
	public class MyReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			//����PlayActivity���͹����Ĳ�ͬ�㲥�����в�ͬ�Ĵ���
			String action = intent.getAction();
			if(Constant.ACTION_PLAY_MUSIC.equals(action)){
				//�յ��������ֵĹ㲥
				//Ҫ���ŵ����ֵ�ַ��Intent��ȡ��
				String url = intent.getStringExtra("url");
				play(url);
			}
			if(Constant.ACTION_PAUSE_MUSIC.equals(action)){
				//�յ���ͣ���ֵĹ㲥
				pauseUrl = intent.getStringExtra("url");
				pause();
			}
			if(Constant.ACTION_SEEKTO_MUSIC.equals(action)){
				//�յ�������ֵĹ㲥
				int progress = intent.getIntExtra("progress",-1);
				//����progress�����Ҫ����������ֲ��Ž���
				seekTime = (progress*mediaPlayer.getDuration())/100;
				mediaPlayer.seekTo(seekTime);
			}
		}
		
	}
		
}
