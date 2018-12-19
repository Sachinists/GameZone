package com.example.gamezone.testing.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SignIn")
public class SignInEntity {
	@Id
	private String id;
	private String email;
	private String image;
	private String name;
	private String provider;
	private String token;
	private String city;
	private String state;
	private String country;
	private String phone;
	private String secondPage;
	private String blocked;
	
	@OneToMany(cascade=CascadeType.ALL)
    @JoinTable(name="LookupTable",
    joinColumns=@JoinColumn(name="userID", referencedColumnName="id"),
    inverseJoinColumns=@JoinColumn(name="favTeamId", referencedColumnName="teamId",unique=true))
    private Set<FavouriteTeamEntity> favouriteTeamEntities;
	
	public Set<FavouriteTeamEntity> getFavouriteTeamEntities() {
		return favouriteTeamEntities;
	}
	public void setFavouriteTeamEntities(Set<FavouriteTeamEntity> favouriteTeamEntities) {
		this.favouriteTeamEntities = favouriteTeamEntities;
	}
	public String getBlocked() {
		return blocked;
	}
	public void setBlocked(String blocked) {
		this.blocked = blocked;
	}
	public String getSecondPage() {
		return secondPage;
	}
	public void setSecondPage(String secondPage) {
		this.secondPage = secondPage;
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
