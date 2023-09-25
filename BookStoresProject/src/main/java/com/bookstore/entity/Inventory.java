package com.bookstore.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "Inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventoryID")
    private Long inventoryID;

    @ManyToOne
    @JoinColumn(name = "bookID", referencedColumnName = "bookID")
    private Book book;

    @Column(name = "stockLevelUsed", nullable = false)
    private Integer stockLevelUsed;

    @Column(name = "stockLevelNew", nullable = false)
    private Integer stockLevelNew;

    @Column(name = "recordCreatedOn", nullable = false)
    private Timestamp recordCreatedOn = new Timestamp(new Date().getTime());

	
    // Constructors
    public Inventory() {
	}


	public Inventory(Long inventoryID, Book book, Integer stockLevelUsed, Integer stockLevelNew,
			Timestamp recordCreatedOn) {
		this.inventoryID = inventoryID;
		this.book = book;
		this.stockLevelUsed = stockLevelUsed;
		this.stockLevelNew = stockLevelNew;
		this.recordCreatedOn = recordCreatedOn;
	}
	
	
	public Inventory(Long inventoryID, Integer stockLevelUsed, Integer stockLevelNew, Timestamp recordCreatedOn) {
		this.inventoryID = inventoryID;
		this.stockLevelUsed = stockLevelUsed;
		this.stockLevelNew = stockLevelNew;
		this.recordCreatedOn = recordCreatedOn;
	}


	//getters, and setters
	
	public Long getInventoryID() {
		return inventoryID;
	}


	public void setInventoryID(Long inventoryID) {
		this.inventoryID = inventoryID;
	}


	public Book getBook() {
		return book;
	}


	public void setBook(Book book) {
		this.book = book;
	}


	public Integer getStockLevelUsed() {
		return stockLevelUsed;
	}


	public void setStockLevelUsed(Integer stockLevelUsed) {
		this.stockLevelUsed = stockLevelUsed;
	}


	public Integer getStockLevelNew() {
		return stockLevelNew;
	}


	public void setStockLevelNew(Integer stockLevelNew) {
		this.stockLevelNew = stockLevelNew;
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
		return "Inventory:\n-------------------------\n\tInventory ID: " + inventoryID  + "\n\t" + book + "\n\tStock Level Used: " + stockLevelUsed
				+ "\n\tStock Level New: " + stockLevelNew + "\n\tRecord Created On: " + recordCreatedOn + "\n";
	}
	
}
