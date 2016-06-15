package com.example.youlu.biz;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.youlu.adapter.MyCalllogAdapter;
import com.example.youlu.adapter.MyNoteAdapter;
import com.example.youlu.adapter.NBCalllogAdapter;
import com.example.youlu.bean.Calllog;
import com.example.youlu.bean.Note;
import com.example.youlu.listener.OnLoadCalllogsFinshListener;
import com.example.youlu.util.YouluUtils;
import com.example.youlu.util.YouluUtils_Calllog;

public class CalllogBiz {
	private Context context;

	public CalllogBiz(Context context){
		this.context = context; 
	}

	public void asyncGetAllCalllogs(OnLoadCalllogsFinshListener listener) {
		YouluUtils_Calllog.asyncGetAllCalllogs(context, listener);
	}

	public void deleteCalllog(NBCalllogAdapter adapter, Calllog calllog) {
		YouluUtils_Calllog.deleteCalllog(context, adapter, calllog);
	}

	public void setName(TextView name, Calllog calllog, ImageView isCallout) {
		YouluUtils.setName(name,calllog,isCallout);
	}

	public void setTime(TextView time, Long time1) {
		YouluUtils.setTime(time,time1);
	}

}
