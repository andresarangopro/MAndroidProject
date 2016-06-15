package com.example.music.activity;

import com.example.music.activity.MyBaseActvity.Position;


public interface IView {
	void setHeaderViewTitle(String title);
	
	void setHeaderViewImage(int resId,Position position);
}
