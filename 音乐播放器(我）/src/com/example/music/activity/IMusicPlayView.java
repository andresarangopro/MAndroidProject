package com.example.music.activity;

public interface IMusicPlayView extends IView{
	//1)唱盘唱针动漫启动
	//2)显示pos所对应的播放列表中的歌曲
	void play(int pos);
	void pause(int pos);
	void updateProgress(int current,int duration);
	void seekTo(int progress);
	void showDownloadDialog(int pos);
}
