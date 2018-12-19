package com.example.gamezone.testing.bean;

import java.util.Random;

public class Notification {
	
	private String userId;
	private String message;
	private String notifyDate;
	private boolean seen;
	private String gameId;
	
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	public boolean isSeen() {
		return seen;
	}
	public void setSeen(boolean seen) {
		this.seen = seen;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNotifyDate() {
		return notifyDate;
	}
	public void setNotifyDate(String notifyDate) {
		this.notifyDate = notifyDate;
	}
	@Override
	public int hashCode() {
		return getMessage().length();
	}
	@Override
	public boolean equals(Object obj) {
		Notification notification = (Notification) obj;
		System.out.println("A: "+getUserId());
		System.out.println("A: "+getGameId());
		System.out.println("B: "+getUserId());
		System.out.println("B: "+getGameId());
		if(getUserId().equals(notification.getUserId()) && getGameId().equals(notification.getGameId()))
			return true;
		return false;
	}
	
	
	
}
