package com.stackroute.cplayer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "C_Player")
public class CPlayer {

	public CPlayer() {
		// TODO Auto-generated constructor stub
	}
	public CPlayer(int id, int pid, String name, String fullName, String userId) {
		this.id=id;
		this.pid = pid;
		this.name = name;
		this.fullName = fullName;
		this.userId = userId;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "PID")
	private int pid;
	@Column(name = "NAME")
	private String name;
	@Column(name = "FULL_NAME")
	private String fullName;
	@Column(name = "USER_ID")
	private String userId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
