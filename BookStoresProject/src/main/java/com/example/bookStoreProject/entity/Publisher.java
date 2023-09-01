package com.example.bookStoreProject.entity;

import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name = "Publisher")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisherID")
    private Long publisherID;

    @Column(name = "publisherName", columnDefinition = "VARCHAR(50) DEFAULT 'Prolifics'")
    private String publisherName;

    @Column(name = "recordCreatedOn", nullable = false)
    private Timestamp recordCreatedOn;

    
    // Constructors
    
    public Publisher() {
	}

    
    public Publisher(Long publisherID, String publisherName, Timestamp recordCreatedOn) {
		this.publisherID = publisherID;
		this.publisherName = publisherName;
		this.recordCreatedOn = recordCreatedOn;
	}

  //getters, and setters
	public Long getPublisherID() {
		return publisherID;
	}


	public void setPublisherID(Long publisherID) {
		this.publisherID = publisherID;
	}


	public String getPublisherName() {
		return publisherName;
	}


	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
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
		return "Publisher:\n--------------------\nPublisher ID: " + publisherID + "\nPublisher Name: " + publisherName + "\nRecord Created On: " + recordCreatedOn + "\n--------------------\n";
	}
    
    
    
	
    
    
    
    
}

