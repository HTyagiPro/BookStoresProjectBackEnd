package com.example.bookStoreProject.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerID")
    private Long customerID;

    @Column(name = "custName", nullable = false)
    private String custName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "custAddress", nullable = false)
    private String custAddress;

    @Column(name = "contactNo", unique = true, nullable = false)
    private Long contactNo;

    @Column(name = "recordCreatedOn", nullable = false)
    private Timestamp recordCreatedOn = new Timestamp(new Date().getTime());

	
    // Constructors
    public Customer() {
	}


	public Customer(Long customerID, String custName, String email, String custAddress, Long contactNo,
			Timestamp recordCreatedOn) {
		this.customerID = customerID;
		this.custName = custName;
		this.email = email;
		this.custAddress = custAddress;
		this.contactNo = contactNo;
		this.recordCreatedOn = recordCreatedOn;
	}

	
	//getters, and setters

	public Long getCustomerID() {
		return customerID;
	}


	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}


	public String getCustName() {
		return custName;
	}


	public void setCustName(String custName) {
		this.custName = custName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCustAddress() {
		return custAddress;
	}


	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}


	public Long getContactNo() {
		return contactNo;
	}


	public void setContactNo(Long contactNo) {
		this.contactNo = contactNo;
	}


	public Timestamp getRecordCreatedOn() {
		return recordCreatedOn;
	}


	public void setRecordCreatedOn(Timestamp recordCreatedOn) {
		this.recordCreatedOn = recordCreatedOn;
	}


	//toString
	
	@Override
	public String toString() {
		return "Customer:\n-------------------------------\n\tCustomer ID: " + customerID + "\n\tCustomer Name: " + custName + "\n\tEmail: " + email + "\n\tCustomer Address: "
				+ custAddress + "\n\tContact No: " + contactNo + "\n\tRecord Created On: " + recordCreatedOn + "\n-------------------------------\n";
	}	
	
}

