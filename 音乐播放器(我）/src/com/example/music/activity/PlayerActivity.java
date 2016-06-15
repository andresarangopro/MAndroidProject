package com.example.music.activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.example.activity.R;
import com.example.music.bean.Music;
import com.example.music.constant.Constant;
import com.example.music.myview.DiscView;
import com.example.music.presenter.IMusicPlayPresenter;
import com.example.music.presenter.MusicPlayPresenterImpl;
import com.example.music.util.ImageLoader;
@EActivity(R.layout.activity_player)
public class PlayerActivity extends MyBaseActvity implements IMusicPlayView{
	int position;
	List<Music> musics; 
	@ViewById(R.id.disc_view)
	DiscView discView;
	@ViewById(R.id.title_view_player)
	View Viewheader;
	@ViewById(R.id.tv_player_current)
	TextView tvCurrent;
	@ViewById(R.id.tv_player_durationtime)
	TextView tvDuration;
	@ViewById(R.id.sb_player_progress)
	SeekBar seekBar;
	@ViewsById({R.id.ib_player_isfav,R.id.ib_player_download,R.id.ib_player_previous,
		R.id.ib_player_play,R.id.ib_play_next})
	List<ImageButton> ibList;

	IMusicPlayPresenter presenter;
	// ImageView ivBack;
	// ��������MusicService���͵Ĺ㲥
	MyReceiver receiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		// ע��㲥����������������MusicService���͵Ĺ㲥
		receiver = new MyReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Constant.ACTION_UPDATE_PROGRESS);
		filter.addAction(Constant.ACTION_UPDATE_MUSIC_PLAYING);
		registerReceiver(receiver, filter);
		
		presenter.onResume();
	}
	

	
	@Override
	protected void onPause() {
		// ע���㲥������
		unregisterReceiver(receiver);
		super.onPause();

	}
	
	@AfterViews
	public void init() {
		position =  getIntent().getIntExtra("position",-1);
		musics = (List<Music>) getIntent().getSerializableExtra("musics");
		presenter = new MusicPlayPresenterImpl(this, musics, position);
		discView.startRotate();
		ImageLoader.loadImage(musics.get(position).getAlbumpic(), discView.getCircleImageView());
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if(fromUser){
					presenter.seekTo(progress);
				}
			}
		});

		Log.d("Tag",position+"");
		Log.d("Tag",musics.toString());
	}

	@Click({R.id.ib_player_isfav,R.id.ib_player_download,R.id.ib_player_previous,R.id.ib_player_play,R.id.ib_play_next})
	public void onClick(View view){
		switch (view.getId()) {
		case R.id.ib_player_isfav:

			break;
		case R.id.ib_player_download:
			presenter.showDownloadDialog();
			break;
		case R.id.ib_player_previous:
			presenter.prev();
			break;
		case R.id.ib_player_play:
			presenter.play();
			break;
		case R.id.ib_play_next:
			presenter.next();
			break;
		}
	}
	@Override
	public void play(int pos) {
		discView.startRotate();
		ImageLoader.loadImage(musics.get(pos).getAlbumpic(), discView.getCircleImageView());
		ibList.get(3).setImageResource(R.drawable.pause);
		
		//��MusicService���Ͳ������ֵĹ㲥
		Intent intent = new Intent(Constant.ACTION_PLAY_MUSIC);
		//��Ҫ���ŵ����ֵ�·����Ϊ�㲥Intent��һ����
		String url = musics.get(pos).getMusicpath();
		intent.putExtra("url",url);
		sendBroadcast(intent);
	
	}

	@Override
	public void pause(int pos) {
		discView.stopRotate();
		ibList.get(3).setImageResource(R.drawable.play);
		//��MusicService������ͣ���ֵĹ㲥
		Intent intent = new Intent(Constant.ACTION_PAUSE_MUSIC);
		//???Ҫ��Ҫ��url???Ҫ������
		String url = musics.get(pos).getMusicpath();
		intent.putExtra("url", url);
		sendBroadcast(intent);
	}

	@Override
	public void updateProgress(int current, int duration) {
		tvCurrent.setText(new SimpleDateFormat("mm:ss").format(current));
		tvDuration.setText(new SimpleDateFormat("mm:ss").format(duration));
		seekBar.setProgress((current*100)/duration);
	}

	@Override
	public void seekTo(int progress) {
		String duration = tvDuration.getText().toString();//05:07
		try {
			Date date = new SimpleDateFormat("mm:ss").parse(duration);
			long current = (date.getTime()*progress)/100;
			tvCurrent.setText(new SimpleDateFormat("mm:ss").format(current));
			
			//��MusicService���Ϳ�����ֵĹ㲥
			Intent intent = new Intent(Constant.ACTION_SEEKTO_MUSIC);
			intent.putExtra("progress", progress);
			sendBroadcast(intent);
		
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}


	
	@Override
	public void showDownloadDialog(final int pos) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(android.R.drawable.ic_menu_info_details);
		builder.setTitle("����֪ͨ");
		builder.setMessage("ȷʵҪ����" + musics.get(pos).getName() + "��?");
		builder.setNegativeButton("������", null);
		builder.setPositiveButton("ȥ����", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				String url = musics.get(pos).getMusicpath();
				presenter.startDownload(url);
			}
		});
		builder.create().show();
	}


	private class MyReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// ��ȡMusicService���͵Ĺ㲥ACTION
			// ���ݲ�ͬ��ACTIONִ�в�ͬ����Ϊ
			String action = intent.getAction();
			if (Constant.ACTION_UPDATE_PROGRESS.equals(action)) {
				// ����sbProgress
				int current = intent.getIntExtra("current", -1);
				int duration = intent.getIntExtra("duration", -1);
				presenter.updateProgress(current, duration);
			}
			if (Constant.ACTION_UPDATE_MUSIC_PLAYING.equals(action)) {
				// ������һ��
				presenter.next();
			}
		}

	}
	
	@Override
	public void setHeaderViewTitle(String title) {
		super.setHeaderViewTitle(Viewheader, title);
	}

	@Override
	public void setHeaderViewImage(int resId, Position position) {
		if(position==Position.LEFT){
			ImageView iv = super.setHeaderViewImage(Viewheader, resId, position);
			iv.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					finish();
				}
			});
		}
	}

}
