package com.example.youlu.bean;

public class Contact {
	private int id;
	private int photo_id;
	private String name;
	private String number;
	private String email;
	private String address;
	private String company;
	private String lookupKey;
	
	public String getLookupKey() {
		return lookupKey;
	}
	public void setLookupKey(String lookuqKey) {
		this.lookupKey = lookuqKey;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	@Override
	public String toString() {
		return "Contact [id=" + id + ", photo_id=" + photo_id + ", name="
				+ name + ", number=" + number + ", email=" + email
				+ ", address=" + address + ", company=" + company
				+ ", lookuqKey=" + lookupKey + "]";
	}
}
