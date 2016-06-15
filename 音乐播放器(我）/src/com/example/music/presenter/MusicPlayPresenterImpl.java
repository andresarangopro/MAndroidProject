package com.example.music.presenter;

import java.util.List;

import android.app.Activity;

import com.example.activity.R;
import com.example.music.activity.IMusicPlayView;
import com.example.music.activity.MyBaseActvity.Position;
import com.example.music.bean.Music;
import com.example.music.model.IMusicPlayModel;
import com.example.music.model.MusicPlayModelImpl;

public class MusicPlayPresenterImpl implements IMusicPlayPresenter{
	//view
		IMusicPlayView view;
		//model
		IMusicPlayModel model;
		
		//歌曲列表musics
		List<Music> musics;
		//当前播放歌曲在歌曲列表中的下标position
		int position;
		
		boolean isPlaying;//用来记录当前歌曲是处于播放(true)/暂停(false)
		
		//构造器
		public MusicPlayPresenterImpl(IMusicPlayView view, List<Music> musics,
				int position) {
			super();
			this.view = view;
			this.musics = musics;
			this.position = position;
			this.model= new MusicPlayModelImpl();
		}
		
		
		@Override
		public void onResume() {
			String title = musics.get(position).getName();
			view.setHeaderViewTitle(title);
			view.setHeaderViewImage(R.drawable.back, Position.LEFT);
			view.setHeaderViewImage(R.drawable.statics, Position.RIGHT);
			view.play(position);
			isPlaying = true;
		}



		@Override
		public void onDestroy() {
			
		}

		@Override
		public void play() {
			if(isPlaying){
				//暂停音乐的播放
				view.pause(position);
				isPlaying = false;
			}else{
				//播放音乐
				view.play(position);
				isPlaying = true;
			}
		}

		@Override
		public void next() {
			//view.pause(position);
			position += 1;
			//如果超过了音乐列表中的文件总数
			if(position>=musics.size()){
				position=0;
			}
			view.play(position);
			view.setHeaderViewTitle(musics.get(position).getName());
			//用户可能在两种情况下，按“下一曲”
			//1)听歌的过程中，直接按“下一曲”
			//2)先按“暂停”键，再按“下一曲”
			isPlaying = true;
		}

		@Override
		public void prev() {
			view.pause(position);
			position -= 1;
			if(position<=0){
				position=musics.size()-1;
			}
			view.play(position);
			view.setHeaderViewTitle(musics.get(position).getName());
			isPlaying = true;
		}

		@Override
		public void showDownloadDialog() {
			view.showDownloadDialog(position);
		}

		@Override
		public void startDownload(String url) {
			model.download((Activity)view,url,musics.get(position).getName());
		}

		@Override
		public void updateProgress(int current, int duration) {
			view.updateProgress(current, duration);
			
		}

		@Override
		public void seekTo(int progress) {
			view.seekTo(progress);
			
		}
}
