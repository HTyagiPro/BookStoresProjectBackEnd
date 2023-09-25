package com.bookstore.entity;

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

  

    @Column(name = "recordCreatedOn", nullable = false)
    private Timestamp recordCreatedOn;

	
    @ManyToOne
    @JoinColumn(name = "bookID", referencedColumnName = "bookID")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "customerID", referencedColumnName = "customerID")
    private Customer customer;
    

    public CartItem(Long id, Integer quantity, Timestamp recordCreatedOn, Book book,
			Customer customer) {
		this.id = id;
		this.quantity = quantity;
		this.recordCreatedOn = recordCreatedOn;
		this.book = book;
		this.customer = customer;
	}


	public CartItem(Long id, Integer quantity, Timestamp recordCreatedOn, Book book) {
		this.id = id;
		this.quantity = quantity;
		this.recordCreatedOn = recordCreatedOn;
		this.book = book;
	}


	public CartItem() {
	}


	public CartItem(Long id, Integer quantity, BigDecimal priceOfUnitQuantity, Timestamp recordCreatedOn) {
		this.id = id;
		this.quantity = quantity;
		this.recordCreatedOn = recordCreatedOn;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
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
		return "Cart Item\n----------------------\n\tID: " + id +"\n\t" +customer + "\n\tQuantity: " + quantity + "\n\tRecord Created On: " + recordCreatedOn + "\n\tBook: " + book + "\n";
	}
    
    
    

	
	
    
    
}
