package com.example.weixin.adapter;

import java.text.SimpleDateFormat;
import java.util.List;

import com.example.weixin.R;
import com.example.weixin.adapter.base.BaseListAdapter;
import com.example.weixin.adapter.base.ViewHolder;
import com.example.weixin.bean.Message;
import com.example.weixin.util.ImageLoadOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import cn.bmob.im.bean.BmobChatUser;

public class MessageAdapter extends BaseListAdapter<Message>{
	
	SimpleDateFormat sdf;
	List<BmobChatUser> friends;
	
	public MessageAdapter(Context context, List<Message> list,List<BmobChatUser> friends) {
		super(context, list);
		this.friends=friends;
	}

	@Override
	public View bindView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_message_received, null);
		}
		ImageView iv_avatar = ViewHolder.get(convertView, R.id.iv_item_message_avatar);
		GridView gv_picContainer = ViewHolder.get(convertView, R.id.gv_item_message_picsContainer);
		TextView tv_name = ViewHolder.get(convertView,R.id.tv_item_message_name);
		TextView tv_comment = ViewHolder.get(convertView, R.id.tv_item_message_Comment);
		TextView tv_content = ViewHolder.get(convertView, R.id.tv_item_message_content);
		TextView tv_praise = ViewHolder.get(convertView, R.id.tv_item_message_praise);
//		TextView tv_time = ViewHolder.get(convertView, R.id.tv_item_message_publish_time);
		TextView tv_phone = ViewHolder.get(convertView, R.id.tv_item_message_phone);
		
		final Message message = getList().get(position);
		for(BmobChatUser friend:friends){
			String username = friend.getUsername();
			String name = message.getName();
			if(username.equals(name)){
				tv_phone.setText(friend.getDeviceType());
				String avatar = friend.getAvatar();
				ImageLoader.getInstance().displayImage(avatar, iv_avatar, ImageLoadOptions.getOptions());
			} else {
				iv_avatar.setImageResource(R.drawable.default_head);
			}
		}
		tv_name.setText(message.getName());
		tv_comment.setText(message.getName()+":"+message.getComments());
		tv_content.setText(message.getText());
		tv_praise.setText(changeToString(message.getPraises()));
		gv_picContainer.setVisibility(View.GONE);
		return convertView;
	}

	private String changeToString(List<String> praises) {
		StringBuilder sb = new StringBuilder();
		for(String string:praises){
			sb.append(string);
			sb.append(",");
		}
		return sb.substring(0,sb.length()-1);
	}


}
