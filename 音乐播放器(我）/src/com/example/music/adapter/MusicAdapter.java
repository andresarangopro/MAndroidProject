package com.example.music.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.activity.R;
import com.example.music.bean.Music;
import com.example.music.biz.MusicBiz;
import com.example.music.myview.CircleImageView;
import com.example.music.util.ImageLoader;

public class MusicAdapter extends BaseAdapter{
	private List<Music> musics;
	private Context context;
	MusicBiz musicBiz;
	public MusicAdapter(Context context, List<Music> musics) {
		this.musics = musics; 
		this.context = context;
		musicBiz = new MusicBiz(context);
		ImageLoader.init(context);
	}

	@Override
	public int getCount() {
		return musics.size();
	}

	@Override
	public Music getItem(int position) {
		return musics.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
		

	public void addAll(List<Music> ms,boolean isClear){
		if(isClear){
			musics.clear();
		}
		musics.addAll(ms);
		this.notifyDataSetChanged();
	}

	public void remove(Music music){
		musics.remove(music);
		notifyDataSetChanged();
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view =null;
		ViewHolder vh=null;
		if(convertView==null){//convertView (可重用的列表项对象)
			view = View.inflate(context, R.layout.item_listview_main_music, null);
			vh=new ViewHolder();

			vh.image=(CircleImageView) view.findViewById(R.id.iv_item_listview_main);

			vh.author=(TextView) view.findViewById(R.id.tv_item_listView_main_author);
			vh.name=(TextView) view.findViewById(R.id.tv_item_listView_main_musicName);
			vh.composer=(TextView) view.findViewById(R.id.tv_item_listView_main_composer);
			vh.durationtime=(TextView) view.findViewById(R.id.tv_item_listView_main_durationtime);
			vh.singer = (TextView) view.findViewById(R.id.tv_item_listView_main_singer);
			
			view.setTag(vh);
		}else{
			view=convertView;
			vh=(ViewHolder)convertView.getTag();
		}
		Music music = getItem(position);
		//设置专辑图片
		
		//ImageUtil.loadImage(music.getAlbumpic(), vh.iv);
		ImageLoader.loadImage(music.getAlbumpic(), vh.image);
		vh.author.setText("词："+music.getAuthor());
		vh.composer.setText("曲："+music.getComposer());
		vh.durationtime.setText(music.getDurationtime());
		vh.name.setText(music.getName());
		vh.singer.setText(music.getSinger());
		
		return view;
	}
	
	public List<Music> getMusics() {
		return musics;
	}
	
	public class ViewHolder{
		CircleImageView image;
		TextView name;
		TextView singer;
		TextView author;
		TextView composer;
		TextView durationtime;
	}

}
