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
	//音乐播放器
	MediaPlayer mediaPlayer;
	//更新播放进度的线程
	Thread updateProgressThread;
	//按下"暂停"键时，音乐播放到的时间
	//当重新开始播放的时候，应该从暂停时位置播放
	int seekTime;
	//音乐播放器是否处在paused状态
	//当播放音乐过程中(mediaPlayer处于started状态)
	//调用mediaPlayer的pause方法，会让mediaPlayer进入paused状态
	boolean isPaused;
	//用来接收PlayActivity发送过来的播放、暂停、快进三种广播
	MyReceiver receiver;
	
	String pauseUrl;//用来保存按下“暂停”键时，歌曲的URL
	@Override
	public void onCreate() {
		super.onCreate();
		//对各种属性进行初始化
		//1)mediaPlayer的赋值
		mediaPlayer = new MediaPlayer();
		//2)mediaPlayer缓冲监听器的添加
		mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
			
			@Override
			public void onPrepared(MediaPlayer mp) {
				mp.start();
				//mediaPlayer.start();
				
			}
		});
		//3)mediaPlayer当前歌曲播放完毕监听器的添加
		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				mp.stop();
				//向PlayActivity发送广播，播放下一首歌曲
				Intent intent = new Intent(Constant.ACTION_UPDATE_MUSIC_PLAYING);
				sendBroadcast(intent);
			}
		});
		//4)更新进度线程的创建和启动
		updateProgressThread = new Thread(){
			public void run() {
				try {
					while(!Thread.interrupted()){
						if(mediaPlayer.isPlaying()){
							//如果mediaPlayer处于播放音乐的状态
							//发送广播，更新PlayActivity的sbProgress
							Intent intent = new Intent(Constant.ACTION_UPDATE_PROGRESS);
							//歌曲当前播放进度
							int currentPosition = mediaPlayer.getCurrentPosition();
							//当前播放歌曲的总长度
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
		
		//5)广播接收器的创建和注册
		receiver = new MyReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Constant.ACTION_PLAY_MUSIC);
		filter.addAction(Constant.ACTION_PAUSE_MUSIC);
		filter.addAction(Constant.ACTION_SEEKTO_MUSIC);
		registerReceiver(receiver, filter );
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		//修改Service的启动方式为非粘性
		return Service.START_NOT_STICKY;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		//对各种属性进行"回收"
		//1)注销广播接收器
		unregisterReceiver(receiver);
		//2)释放mediaPlayer
		mediaPlayer.release();
		//3)结束进度更新线程
		//Thread中有这样一个属性boolean flag
		//调用interrupt方法，会让该属性从false-->true
		//同时，如果当前Thread对象是处于可中断的阻塞状态时(sleep中)
		//会抛出一个InterruptException异常
		//只要Thread.interrupted()检测到ture或者
		//抛出的这个InterruptException异常被捕获
		//flag属性就可以从ture回到false
		updateProgressThread.interrupt();
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	/**
	 * MusicServie用来播放指定位置音乐的方法
	 * @param url
	 */
	private void play(String url){
		try {
			if(isPaused && url.equals(pauseUrl)){
				//从暂停状态开始继续播放歌曲
				mediaPlayer.start();//paused--->started
				mediaPlayer.seekTo(seekTime);//移动到暂停时的位置
				isPaused = false;
				seekTime = 0;
			}else{
				isPaused = false;
				seekTime = 0;
				//全新播放一首歌曲
				mediaPlayer.reset();
				mediaPlayer.setDataSource(url);
				mediaPlayer.prepareAsync();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * MusicService用来暂停音乐播放的方法
	 */
	private void pause(){
		isPaused = true;
		seekTime = mediaPlayer.getCurrentPosition();
		mediaPlayer.pause();
	}
	
	
	public class MyReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			//根据PlayActivity发送过来的不同广播，进行不同的处理
			String action = intent.getAction();
			if(Constant.ACTION_PLAY_MUSIC.equals(action)){
				//收到播放音乐的广播
				//要播放的音乐地址从Intent中取出
				String url = intent.getStringExtra("url");
				play(url);
			}
			if(Constant.ACTION_PAUSE_MUSIC.equals(action)){
				//收到暂停音乐的广播
				pauseUrl = intent.getStringExtra("url");
				pause();
			}
			if(Constant.ACTION_SEEKTO_MUSIC.equals(action)){
				//收到快进音乐的广播
				int progress = intent.getIntExtra("progress",-1);
				//利用progress换算出要快进到的音乐播放进度
				seekTime = (progress*mediaPlayer.getDuration())/100;
				mediaPlayer.seekTo(seekTime);
			}
		}
		
	}
		
}
