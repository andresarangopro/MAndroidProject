package com.example.music.constant;

/**
 * 常量类 定义各种广播的ACTION内容
 * 
 * @author pjy
 *
 */
public class Constant {
	/**
	 * 由PlayActivity发送给MusicService的广播
	 */
	// 播放音乐的广播
	public static final String ACTION_PLAY_MUSIC = "com.tarena.ACTION_PLAY_MUSIC";
	// 暂停音乐的广播
	public static final String ACTION_PAUSE_MUSIC = "com.tarena.ACTION_PAUSE_MUSIC";
	// 拖动进度条时快进音乐的广播
	public static final String ACTION_SEEKTO_MUSIC = "com.tarena.ACTION_SEEKTO_MUSIC";

	/**
	 * 由MusicService发送给PlayActivity的广播
	 */
	// 更新PlayActivity中进度条的广播
	public static final String ACTION_UPDATE_PROGRESS = "com.tarena. ACTION_UPDATE_PROGRESS";
	// 当前歌曲播放完毕后播放下一首歌曲的广播
	public static final String ACTION_UPDATE_MUSIC_PLAYING = "com.tarena.ACTION_UPDATE_MUSIC_PLAYING";

}
