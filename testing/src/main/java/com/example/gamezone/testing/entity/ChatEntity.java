package com.example.gamezone.testing.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "chat")
@GenericGenerator(name = "pkgen", strategy = "increment")
public class ChatEntity {
	
	@Id
	@GeneratedValue(generator = "pkgen")
	private Integer cID;
	private String sender;
    private String dateOfSending;
	private Long teamId;
    private String message;
    private boolean readUnread;
    private String sID;
    
    
    
    public String getsID() {
		return sID;
	}
	public void setsID(String sID) {
		this.sID = sID;
	}
	public boolean isReadUnread() {
		return readUnread;
	}
	public void setReadUnread(boolean readUnread) {
		this.readUnread = readUnread;
	}
	public String getDateOfSending() {
		return dateOfSending;
	}
	public void setDateOfSending(String dateOfSending) {
		this.dateOfSending = dateOfSending;
	}
	

	
    public Integer getcID() {
		return cID;
	}
	public void setcID(Integer cID) {
		this.cID = cID;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
    
}
