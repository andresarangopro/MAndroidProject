package com.example.music.activity;

import java.util.ArrayList;
import java.util.List;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.activity.R;
import com.example.music.adapter.MusicAdapter;
import com.example.music.bean.Music;
import com.example.music.presenter.MusicListPresenterImpl;
import com.example.music.server.MusicService;
@EActivity(R.layout.activity_main)
public class MainActivity extends MyBaseActvity implements IMusicListView{
	public static final String MUSICS = "musics";
	public static final String POSITION = "position";

	@ViewById(R.id.title_view_main)
	View headerView;
	@ViewById(R.id.lv_main_music_container)
	ListView listView;
	MusicListPresenterImpl presenter;
	MusicAdapter adapter;
	ProgressDialog pd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}

	@Override
	protected void onResume() {
		super.onResume();
		presenter.onResume();
		presenter.showMusicList();
	}
	private void initView() {
		presenter = new MusicListPresenterImpl(this);
	}
	@Override
	public void setHeaderViewTitle(String title) {
		setHeaderViewTitle(headerView, title);
	}


	@Override
	public void showMusicList(List<Music> musics) {
		adapter = new MusicAdapter(this, musics);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				Intent intent2 = new Intent(MainActivity.this,MusicService.class);
				startService(intent2);

				Intent intent = new Intent(MainActivity.this,PlayerActivity_.class);
				intent.putExtra(MUSICS,(ArrayList<Music>)adapter.getMusics());
				intent.putExtra(POSITION, position);
				presenter.jumpTo(MainActivity.this, intent);
			}
		});
	}


	@Override
	public void showLoadingProgress() {
		pd.show();
	}

	@Override
	public void dismissLoadingProgress() {
		pd.dismiss();
	}

	@Override
	public void setHeaderViewImage(int resId, Position position) {

	}


}
