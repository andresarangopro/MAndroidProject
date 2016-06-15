package com.example.youlu.bean;

public class Sms {
	private int _id;
	private int type;
	private String body;
	private long time;
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Sms [_id=" + _id + ", type=" + type + ", body=" + body
				+ ", time=" + time + "]";
	}
}
