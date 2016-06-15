package com.example.music.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.music.bean.Music;

public class JSonUtil {
	private static final String HOST="http://192.168.12.103:8080/MusicServer/";
	public static List<Music> getListByJson(String s)throws JSONException {
		JSONObject object = new JSONObject(s);
		JSONArray arr = object.getJSONArray("data");
		final List<Music> musics = new ArrayList<Music>();
		for(int i=0;i<arr.length();i++){
			JSONObject jobject = arr.getJSONObject(i);
			Music music = new Music();
			music.setId(jobject.getInt("id"));
			music.setAlbum(jobject.getString("album"));
			music.setAlbumpic(HOST+jobject.getString("albumpic"));
			music.setAuthor(jobject.getString("author"));
			music.setComposer(jobject.getString("composer"));
			music.setDowncount(jobject.getString("downcount"));
			music.setDurationtime(jobject.getString("durationtime"));
			music.setFavcount(jobject.getString("favcount"));
			music.setMusicpath(HOST+jobject.getString("musicpath"));
			music.setName(jobject.getString("name"));
			music.setSinger(jobject.getString("singer"));
			musics.add(music);
		}
		return musics;
	}
}
