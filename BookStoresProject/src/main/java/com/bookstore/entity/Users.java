package com.bookstore.entity;

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
    
    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phoneNo", unique = true, nullable = false)
    private Long phoneNo;
    
    @Column(name = "status", unique = false, nullable = false)
    private String status;
    
    @Column(name = "role", unique = false, nullable = false)
    private String role;


    @Column(name = "registrationDate", nullable = true)
    private LocalDateTime registrationDate;

    // Constructors, 
    public Users() {
	}
	
    
    
    public Users(Long userID, String username, String passcode, String email, String name, String address, Long phoneNo,
			LocalDateTime registrationDate) {
		this.userID = userID;
		this.username = username;
		this.passcode = passcode;
		this.email = email;
		this.name = name;
		this.address = address;
		this.phoneNo = phoneNo;
		this.registrationDate = registrationDate;
	}



	public Users(Long userID, String username, String passcode, String email, String name, String address,
			Long phoneNo) {
		this.userID = userID;
		this.username = username;
		this.passcode = passcode;
		this.email = email;
		this.name = name;
		this.address = address;
		this.phoneNo = phoneNo;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public Long getPhoneNo() {
		return phoneNo;
	}



	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
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
		return "Users:\n------------------------\n\tUser ID: " + userID  +"\n\tName: " + name +"\n\t Phone No.:" + phoneNo + "\n\tAddress: " + address + "\n\tUsername: "+ username + "\n\tPasscode: " + passcode + "\n\tEmail: " + email
				+ "\n\tRegistration Date: " + registrationDate + "\n------------------------\n";
	} 
	
	
	
    
}

