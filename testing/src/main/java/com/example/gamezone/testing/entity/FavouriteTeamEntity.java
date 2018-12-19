package com.example.gamezone.testing.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FavTeam")
public class FavouriteTeamEntity {
	@Id
	private Integer teamId;
//	private boolean isNBAFranchise;
//	private boolean isAllStar;
    private String city;
    private String altCityName;
    private String fullName;
    private String tricode;
//    private String nickname;
//    private String urlName;
//    private String confName;
//    private String divName;
	
    public Integer getId() {
		return teamId;
	}
	public void setId(Integer favTeamId) {
		this.teamId = favTeamId;
	}
//	public boolean isNBAFranchise() {
//		return isNBAFranchise;
//	}
//	public void setNBAFranchise(boolean isNBAFranchise) {
//		this.isNBAFranchise = isNBAFranchise;
//	}
//	public boolean isAllStar() {
//		return isAllStar;
//	}
//	public void setAllStar(boolean isAllStar) {
//		this.isAllStar = isAllStar;
//	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAltCityName() {
		return altCityName;
	}
	public void setAltCityName(String altCityName) {
		this.altCityName = altCityName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getTricode() {
		return tricode;
	}
	public void setTricode(String tricode) {
		this.tricode = tricode;
	}
//	public String getNickname() {
//		return nickname;
//	}
//	public void setNickname(String nickname) {
//		this.nickname = nickname;
//	}
//	public String getUrlName() {
//		return urlName;
//	}
//	public void setUrlName(String urlName) {
//		this.urlName = urlName;
//	}
//	public String getConfName() {
//		return confName;
//	}
//	public void setConfName(String confName) {
//		this.confName = confName;
//	}
//	public String getDivName() {
//		return divName;
//	}
//	public void setDivName(String divName) {
//		this.divName = divName;
//	}
    
    
}
