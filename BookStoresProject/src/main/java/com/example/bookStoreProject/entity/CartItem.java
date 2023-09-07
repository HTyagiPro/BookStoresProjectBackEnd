package com.example.bookStoreProject.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.*;

@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "priceOfUnitQuantity", nullable = false)
    private BigDecimal priceOfUnitQuantity;

    @Column(name = "recordCreatedOn", nullable = false)
    private Timestamp recordCreatedOn;

	
    @ManyToOne
    @JoinColumn(name = "bookID", referencedColumnName = "bookID")
    private Book book;


	public CartItem(Long id, Integer quantity, BigDecimal priceOfUnitQuantity, Timestamp recordCreatedOn, Book book) {
		this.id = id;
		this.quantity = quantity;
		this.priceOfUnitQuantity = priceOfUnitQuantity;
		this.recordCreatedOn = recordCreatedOn;
		this.book = book;
	}


	public CartItem() {
	}


	public CartItem(Long id, Integer quantity, BigDecimal priceOfUnitQuantity, Timestamp recordCreatedOn) {
		this.id = id;
		this.quantity = quantity;
		this.priceOfUnitQuantity = priceOfUnitQuantity;
		this.recordCreatedOn = recordCreatedOn;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public BigDecimal getPriceOfUnitQuantity() {
		return priceOfUnitQuantity;
	}


	public void setPriceOfUnitQuantity(BigDecimal priceOfUnitQuantity) {
		this.priceOfUnitQuantity = priceOfUnitQuantity;
	}


	public Timestamp getRecordCreatedOn() {
		return recordCreatedOn;
	}


	public void setRecordCreatedOn(Timestamp recordCreatedOn) {
		this.recordCreatedOn = recordCreatedOn;
	}


	public Book getBook() {
		return book;
	}


	public void setBook(Book book) {
		this.book = book;
	}


	@Override
	public String toString() {
		return "Cart Item\n----------------------\n\tID: " + id + "\n\tQuantity: " + quantity + "\n\tPrice Of Unit Quantity: " + priceOfUnitQuantity
				+ "\n\tRecord Created On: " + recordCreatedOn + "\n\tBook: " + book + "\n";
	}
    
    
    

	
	
    
    
}
