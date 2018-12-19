package com.example.gamezone.testing.bean;

public class ExtraDetails {
	private String qwerty;
	private String city;
	private String state;
	private String country;
	private String phone;
	
	public String getQwerty() {
		return qwerty;
	}
	public void setQwerty(String qwerty) {
		this.qwerty = qwerty;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getQwerty()+" : "+getCity()+" : "+getCountry()+" : "+getPhone()+" : "+getState();
	}
	
	
    
}
