package com.example.gamezone.testing.bean;

import java.util.Set;

public class SignIn {
	private String email;
	private String id;
	private String image;
	private String name;
	private String provider;
	private String token;
	
	private String city;
	private String state;
	private String country;
	private String phone;
	private String secondPage;
	private Set<FavouriteTeam> favouriteTeam;
	private String blocked;
	
	



	public Set<FavouriteTeam> getFavouriteTeam() {
		return favouriteTeam;
	}
	public void setFavouriteTeam(Set<FavouriteTeam> favouriteTeam) {
		this.favouriteTeam = favouriteTeam;
	}
	public String getBlocked() {
		return blocked;
	}
	public void setBlocked(String blocked) {
		this.blocked = blocked;
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
	public String getSecondPage() {
		return secondPage;
	}
	public void setSecondPage(String secondPage) {
		this.secondPage = secondPage;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "Name: "+getName()+"\nEmail: "+getEmail()+"\nToken: "+getToken()+"\nID: "+getId()+"\nImage: "+getImage()+"\nProvider: "+getProvider();
	}
	
	
	
}
