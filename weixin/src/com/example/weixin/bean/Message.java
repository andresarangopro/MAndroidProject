package com.example.weixin.bean;

import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class Message extends BmobObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Text;
	private String name;
	private List<BmobFile> pics;
	private List<String> praises;
	private long createTime;
	private long updateManagerdAt;
	private List<Comment> comments;

	public String getText() {
		return Text;
	}
	public void setText(String text) {
		Text = text;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<BmobFile> getPics() {
		return pics;
	}
	public void setPics(List<BmobFile> pics) {
		this.pics = pics;
	}
	public List<String> getPraises() {
		return praises;
	}
	public void setPraises(List<String> praises) {
		this.praises = praises;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getUpdateManagerdAt() {
		return updateManagerdAt;
	}
	
	public void setUpdateManagerdAt(long updateManagerdAt) {
		this.updateManagerdAt = updateManagerdAt;
	}

	public List<Comment> getComments() {
		return comments;
	}
	
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
