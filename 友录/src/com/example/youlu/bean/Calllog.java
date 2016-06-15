package com.example.youlu.bean;

public class Calllog {
	private int _id;//数据记录id
	private int photo_id; //头像id
	private String name;//姓名
	private String number;//电话号码
	private long time;//电话发生的时间
	private int type; //type的取值只为1，2，3
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public int getPhoto_id() {
		return photo_id;
	}
	public void setPhoto_id(int photo_id) {
		this.photo_id = photo_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Calllog [_id=" + _id + ", photo_id=" + photo_id + ", name="
				+ name + ", number=" + number + ", time=" + time + ", type="
				+ type + "]";
	}
}
