package com.example.music.activity;

public interface IMusicPlayView extends IView{
	//1)���̳��붯������
	//2)��ʾpos����Ӧ�Ĳ����б��еĸ���
	void play(int pos);
	void pause(int pos);
	void updateProgress(int current,int duration);
	void seekTo(int progress);
	void showDownloadDialog(int pos);
}
