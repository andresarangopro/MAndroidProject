package com.example.music.constant;

/**
 * ������ ������ֹ㲥��ACTION����
 * 
 * @author pjy
 *
 */
public class Constant {
	/**
	 * ��PlayActivity���͸�MusicService�Ĺ㲥
	 */
	// �������ֵĹ㲥
	public static final String ACTION_PLAY_MUSIC = "com.tarena.ACTION_PLAY_MUSIC";
	// ��ͣ���ֵĹ㲥
	public static final String ACTION_PAUSE_MUSIC = "com.tarena.ACTION_PAUSE_MUSIC";
	// �϶�������ʱ������ֵĹ㲥
	public static final String ACTION_SEEKTO_MUSIC = "com.tarena.ACTION_SEEKTO_MUSIC";

	/**
	 * ��MusicService���͸�PlayActivity�Ĺ㲥
	 */
	// ����PlayActivity�н������Ĺ㲥
	public static final String ACTION_UPDATE_PROGRESS = "com.tarena. ACTION_UPDATE_PROGRESS";
	// ��ǰ����������Ϻ󲥷���һ�׸����Ĺ㲥
	public static final String ACTION_UPDATE_MUSIC_PLAYING = "com.tarena.ACTION_UPDATE_MUSIC_PLAYING";

}
