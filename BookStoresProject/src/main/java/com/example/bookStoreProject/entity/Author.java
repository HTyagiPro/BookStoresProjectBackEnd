package com.example.bookStoreProject.entity;

//import java.sql.Timestamp;
import java.sql.Timestamp;
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
    private Timestamp recordCreatedOn;

	
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


	@Override
	public String toString() {
		return "Author:\n-------------------\nAuthor ID: " + authorID + "\nAuthor Name: " + authorName + "\nRecord Created On: " + recordCreatedOn + "\n-------------------\n";
	}
    
    
    //toString
	
	
	
	
}
