package com.example.gamezone.testing.bean;

public class FavouriteTeam {

	private String userId;
	private Integer teamId;
	private boolean isNBAFranchise;
	private boolean isAllStar;
	private String city;
	private String altCityName;
	private String fullName;
	private String tricode;
	

    private String nickname;
    private String urlName;
    private String confName;
    private String divName;
	public boolean isNBAFranchise() {
		return isNBAFranchise;
	}
	public void setNBAFranchise(boolean isNBAFranchise) {
		this.isNBAFranchise = isNBAFranchise;
	}
	public boolean isAllStar() {
		return isAllStar;
	}
	public void setAllStar(boolean isAllStar) {
		this.isAllStar = isAllStar;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
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

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUrlName() {
		return urlName;
	}
	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}
	public String getConfName() {
		return confName;
	}
	public void setConfName(String confName) {
		this.confName = confName;
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	

}
