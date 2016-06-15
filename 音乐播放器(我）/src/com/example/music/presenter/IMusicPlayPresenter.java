package com.example.music.presenter;

public interface IMusicPlayPresenter extends IPresenter{
	void play();// 点击PlayActivity中的播放/暂停按钮的时候，该方法会被调用

	void next();// 点击PlayActivity中的下一曲按钮的时候，该方法会被调用

	void prev();// 点击PlayActivity中的上一曲按钮的时候，该方法会被调用

	void showDownloadDialog();// 点击PlayActivity中下载按钮的时候，该方法会被调用

	void startDownload(String url);// 调用Model层中的一个下载方法

	void updateProgress(int current, int duration);

	void seekTo(int progress);
}
