package com.example.music.presenter;

import android.content.Context;
import android.content.Intent;


public interface IMusicListPresenter extends  IPresenter{
	void showMusicList();
	void jumpTo(Context context,Intent intent);
}
