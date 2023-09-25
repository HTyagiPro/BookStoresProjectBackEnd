package com.bookstore.entity;

import java.sql.Timestamp;
import java.util.Date;

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
    private Timestamp recordCreatedOn = new Timestamp(new Date().getTime());

    
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
		return "Publisher:\n--------------------\n\tPublisher ID: " + publisherID + "\n\tPublisher Name: " + publisherName + "\n\tRecord Created On: " + recordCreatedOn + "\n";
	}
    
  
    
}

