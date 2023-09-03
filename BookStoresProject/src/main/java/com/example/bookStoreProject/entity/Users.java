package com.example.bookStoreProject.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "Users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID")
    private Long userID;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "passcode", nullable = false)
    private String passcode;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "registrationDate", nullable = false)
    private LocalDateTime registrationDate;

    // Constructors, 
    public Users() {
	}
	
    public Users(Long userID, String username, String passcode, String email, LocalDateTime registrationDate) {
		this.userID = userID;
		this.username = username;
		this.passcode = passcode;
		this.email = email;
		this.registrationDate = registrationDate;
	}
    
    //getters, and setters
	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasscode() {
		return passcode;
	}

	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Override
	public String toString() {
		return "Users:\n------------------------\n\tUser ID: " + userID + "\n\tUsername: " + username + "\n\tPasscode: " + passcode + "\n\tEmail: " + email
				+ "\n\tRegistration Date: " + registrationDate + "\n------------------------\n";
	} 
    
    
}

