package com.garwan.assignment.auth;

import javax.persistence.*;


@Entity
@Table(
    name="Account", 
    indexes = {
       @Index(name = "USER_NAME_INDX_0", columnList = "user_name", unique = true),  
   })
public class Account  {
	static final long serialVersionUID = 6730587226782066359L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	
	@Column(name = "user_name", nullable = false, length = 255)
	private String userName;
	
	@Column(name = "password_hash", nullable = false, length = 160)
	private String passwordHash;

	public Account(String username, String password) {
		this.userName = username;
		this.passwordHash = password;
	}
	
	public Account() {
		
	}
	
	// Generated getters & setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
}
