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
		
		//�����б�musics
		List<Music> musics;
		//��ǰ���Ÿ����ڸ����б��е��±�position
		int position;
		
		boolean isPlaying;//������¼��ǰ�����Ǵ��ڲ���(true)/��ͣ(false)
		
		//������
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
				//��ͣ���ֵĲ���
				view.pause(position);
				isPlaying = false;
			}else{
				//��������
				view.play(position);
				isPlaying = true;
			}
		}

		@Override
		public void next() {
			//view.pause(position);
			position += 1;
			//��������������б��е��ļ�����
			if(position>=musics.size()){
				position=0;
			}
			view.play(position);
			view.setHeaderViewTitle(musics.get(position).getName());
			//�û���������������£�������һ����
			//1)����Ĺ����У�ֱ�Ӱ�����һ����
			//2)�Ȱ�����ͣ�������ٰ�����һ����
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
