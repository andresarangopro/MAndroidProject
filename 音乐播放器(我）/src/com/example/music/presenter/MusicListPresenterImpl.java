package com.example.music.presenter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.music.activity.IMusicListView;
import com.example.music.bean.Music;
import com.example.music.listener.OnLoadMusicFinishListener;
import com.example.music.model.IMusicListModel;
import com.example.music.model.MusicListModelImpl;

public class MusicListPresenterImpl implements IMusicListPresenter{
	IMusicListView view;
	IMusicListModel model;
	
	public MusicListPresenterImpl(IMusicListView view) {
		super();
		this.view = view;
		this.model = new MusicListModelImpl();
	}

	@Override
	public void onResume() {
		view.setHeaderViewTitle("¸èÇúÁÐ±í");
	}

	@Override
	public void onDestroy() {
		
	}

	@Override
	public void showMusicList() {
//		view.showLoadingProgress();	
		model.getMusicList(new OnLoadMusicFinishListener() {
			@Override
			public void OnLoadMusicFinish(List<Music> musics) {
				Log.d("Tag", musics.toString());
				view.showMusicList(musics);
			}
		});
	}

	@Override
	public void jumpTo(Context context, Intent intent) {
		context.startActivity(intent);
	}

}
