package com.example.youlu.bean;

public class Note {
	private int _id;        //_id
	private int photo_id;   //ͷ��id
	private String name;    //����
	private String number;  //�绰
	private int read ;     //1 �Ѷ� 0 δ��
	private String body;    //�Ự�����(����)һ������                 ������
	private long date;    //������ʱ��
	private int type;
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
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
	public int getRead() {
		return read;
	}
	public void setRead(int read) {
		this.read = read;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Note [_id=" + _id + ", photo_id=" + photo_id + ", name=" + name
				+ ", number=" + number + ", read=" + read + ", body=" + body
				+ ", date=" + date + ", type=" + type + "]";
	}
}
