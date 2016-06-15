package com.example.music.activity;

import java.util.List;

import com.example.music.bean.Music;

public interface IMusicListView extends IView {
	//��ʾ����
	void showMusicList(List<Music> musics);
	
	void showLoadingProgress();
	
	void dismissLoadingProgress();
}
