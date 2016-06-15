package com.example.music.presenter;

public interface IMusicPlayPresenter extends IPresenter{
	void play();// ���PlayActivity�еĲ���/��ͣ��ť��ʱ�򣬸÷����ᱻ����

	void next();// ���PlayActivity�е���һ����ť��ʱ�򣬸÷����ᱻ����

	void prev();// ���PlayActivity�е���һ����ť��ʱ�򣬸÷����ᱻ����

	void showDownloadDialog();// ���PlayActivity�����ذ�ť��ʱ�򣬸÷����ᱻ����

	void startDownload(String url);// ����Model���е�һ�����ط���

	void updateProgress(int current, int duration);

	void seekTo(int progress);
}
