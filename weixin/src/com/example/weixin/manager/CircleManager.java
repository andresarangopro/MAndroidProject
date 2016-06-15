package com.example.weixin.manager;

import java.util.ArrayList;
import java.util.List;

import com.example.weixin.bean.Message;
import com.example.weixin.listener.OnLoadMessageFinishListener;

import android.content.Context;
import android.util.Log;
import cn.bmob.im.bean.BmobChatUser;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class CircleManager{

	Context context;

	public CircleManager(Context context){
		this.context = context;
	}

	public void SendTextToCircle(String name,String text){
		
	}

	public void getMessages(List<BmobChatUser> friends,OnLoadMessageFinishListener listener){
		ArrayList<String> friendNames = new ArrayList<String>(); 
		for(BmobChatUser friend:friends){
			String friendName = friend.getUsername();
			friendNames.add(friendName);
		}
		searchMassage(friendNames,listener);
	}

	private void searchMassage(ArrayList<String> friendNames,final OnLoadMessageFinishListener listener){
		BmobQuery<Message> query = new BmobQuery<Message>();
		query.addWhereContainedIn("name",friendNames);
		query.setLimit(10);
		query.findObjects(context, new FindListener<Message>() {

			@Override
			public void onSuccess(List<Message> messges) {
				listener.OnLoadMessageFinish(messges);
			}
			@Override
			public void onError(int arg0, String arg1) {
				Log.d("life",arg1);
			}
		});
	};
}
