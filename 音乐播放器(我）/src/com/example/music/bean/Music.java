package com.example.music.bean;

import java.io.Serializable;

public class Music implements Serializable{
	private String album;
	private String albumpic;
	private String author;
	private String composer;
	private String downcount;
	private String durationtime;
	private String favcount;
	private int id;
	private String musicpath;
	private String name;
	private String singer;
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getAlbumpic() {
		return albumpic;
	}
	public void setAlbumpic(String albumpic) {
		this.albumpic = albumpic;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getComposer() {
		return composer;
	}
	public void setComposer(String composer) {
		this.composer = composer;
	}
	public String getDowncount() {
		return downcount;
	}
	public void setDowncount(String downcount) {
		this.downcount = downcount;
	}
	public String getDurationtime() {
		return durationtime;
	}
	public void setDurationtime(String durationtime) {
		this.durationtime = durationtime;
	}
	public String getFavcount() {
		return favcount;
	}
	public void setFavcount(String favcount) {
		this.favcount = favcount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMusicpath() {
		return musicpath;
	}
	public void setMusicpath(String musicpath) {
		this.musicpath = musicpath;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	@Override
	public String toString() {
		return "Music [album=" + album + ", albumpic=" + albumpic + ", author="
				+ author + ", composer=" + composer + ", downcount="
				+ downcount + ", durationtime=" + durationtime + ", favcount="
				+ favcount + ", id=" + id + ", musicpath=" + musicpath
				+ ", name=" + name + ", singer=" + singer + "]";
	}
}
