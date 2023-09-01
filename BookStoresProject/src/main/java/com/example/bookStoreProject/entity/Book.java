package com.example.bookStoreProject.entity;

import java.math.BigDecimal;
//import java.security.Timestamp;
import java.sql.Timestamp;
import javax.persistence.*;

@Entity
@Table(name = "Books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookID")
    private Long bookID;

    @ManyToOne
    @JoinColumn(name = "authorID", referencedColumnName = "authorID")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "publisherID", referencedColumnName = "publisherID")
    private Publisher publisher;

    @Column(name = "title", unique = true, nullable = false)
    private String title;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "ISBNcode", unique = true, nullable = false)
    private Long iSBNcode;

    @Column(name = "pageCount", nullable = false)
    private Integer pageCount;

    @Column(name = "publishedYear", nullable = false)
    private Integer publishedYear;

    @Column(name = "bookCondition", nullable = false)
    private String bookCondition;

    @Column(name = "recordCreatedOn", nullable = false)
    private Timestamp recordCreatedOn;

    
    // Constructors,
    
	public Book() {
	}
    
    
    public Book(Long bookID, Author author, Publisher publisher, String title, String category, BigDecimal price,
			Long iSBNcode, Integer pageCount, Integer publishedYear, String bookCondition, Timestamp recordCreatedOn) {
		this.bookID = bookID;
		this.author = author;
		this.publisher = publisher;
		this.title = title;
		this.category = category;
		this.price = price;
		this.iSBNcode = iSBNcode;
		this.pageCount = pageCount;
		this.publishedYear = publishedYear;
		this.bookCondition = bookCondition;
		this.recordCreatedOn = recordCreatedOn;
	}


	//Getters, and Setters
    
	public Long getBookID() {
		return bookID;
	}


	public void setBookID(Long bookID) {
		this.bookID = bookID;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getISBNcode() {
		return iSBNcode;
	}

	public void setISBNcode(Long iSBNcode) {
		this.iSBNcode = iSBNcode;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getPublishedYear() {
		return publishedYear;
	}

	public void setPublishedYear(Integer publishedYear) {
		this.publishedYear = publishedYear;
	}

	public String getBookCondition() {
		return bookCondition;
	}

	public void setBookCondition(String bookCondition) {
		this.bookCondition = bookCondition;
	}

	public Timestamp getRecordCreatedOn() {
		return recordCreatedOn;
	}

	public void setRecordCreatedOn(Timestamp recordCreatedOn) {
		this.recordCreatedOn = recordCreatedOn;
	}

	@Override
	public String toString() {
		return "Book:\n--------------------------------\nBook ID: " + bookID + "\n\t " + author + "\n\t"+ publisher + "\nTitle: " + title
				+ "\nCategory: " + category + "\nPrice: " + price + "\nI.S.B.N. Code: " + iSBNcode + "\nPage Count: " + pageCount
				+ "\nPublished Year: " + publishedYear + "\nBook Condition: " + bookCondition + "\nRecord Created On: "
				+ recordCreatedOn + "\n--------------------------------\n";
	}

   
    
    
    
}
