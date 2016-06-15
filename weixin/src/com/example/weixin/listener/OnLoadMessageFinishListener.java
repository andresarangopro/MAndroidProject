package com.example.weixin.listener;

import java.util.List;

import com.example.weixin.bean.Message;

public interface OnLoadMessageFinishListener {
	void OnLoadMessageFinish(List<Message> messges);
}
