package com.bookstore.entity;

//import java.sql.Timestamp;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "Author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authorID")
    private Long authorID;

    @Column(name = "authorName", columnDefinition = "VARCHAR(50) DEFAULT 'Anonymous'")
    private String authorName;

    @Column(name = "recordCreatedOn", nullable = false)
    private Timestamp recordCreatedOn = new Timestamp(new Date().getTime());

	
    // Constructors
    
    public Author() {
	}


	public Author(Long authorID, String authorName, Timestamp recordCreatedOn) {
		this.authorID = authorID;
		this.authorName = authorName;
		this.recordCreatedOn = recordCreatedOn;
	}


	//Getters, and Setters
	
	public Long getAuthorID() {
		return authorID;
	}


	public void setAuthorID(Long authorID) {
		this.authorID = authorID;
	}


	public String getAuthorName() {
		return authorName;
	}


	public void setAuthorName(String authorName) {
		this.authorName = authorName;
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
		return "Author:\n-------------------\n\tAuthor ID: " + authorID + "\n\tAuthor Name: " + authorName + "\n\tRecord Created On: " + recordCreatedOn + "\n";
	}
    
    
    
	
	
	
	
}
