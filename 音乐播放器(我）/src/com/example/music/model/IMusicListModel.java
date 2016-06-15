package com.example.music.model;

import com.example.music.listener.OnLoadMusicFinishListener;

public interface IMusicListModel extends IModel{
	void getMusicList(OnLoadMusicFinishListener listener);
}
