package com.SportyShoes.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int u_id;
	private String userName;
	private String userEmail;
	public int getU_Id() {
		return u_id;
	}
	public void setId(int id) {
		this.u_id = id;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int u_id, String userName, String userEmail) {
		super();
		this.u_id = u_id;
		this.userName = userName;
		this.userEmail = userEmail;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
}
