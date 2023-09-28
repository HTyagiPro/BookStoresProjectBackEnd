package com.bookstore.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "conditions", nullable = false)
    private String conditions;

    @Column(name = "recordCreatedOn", nullable = false)
    private Timestamp recordCreatedOn;

    @ManyToOne
    @JoinColumn(name = "bookID", referencedColumnName = "bookID")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "customerID", referencedColumnName = "customerID")
    private Customer customer;

    // Constructors

    // Parameterized constructor with all fields
    public CartItem(Long id, Integer quantity, String conditions, Timestamp recordCreatedOn, Book book,
            Customer customer) {
        this.id = id;
        this.quantity = quantity;
        this.conditions = conditions;
        this.recordCreatedOn = recordCreatedOn;
        this.book = book;
        this.customer = customer;
    }

    // Parameterized constructor without customer field
    public CartItem(Long id, Integer quantity, String conditions, Timestamp recordCreatedOn, Book book) {
        this.id = id;
        this.quantity = quantity;
        this.conditions = conditions;
        this.recordCreatedOn = recordCreatedOn;
        this.book = book;
    }

    // Default constructor
    public CartItem() {
    }

    // Constructor without quantity, conditions, and priceOfUnitQuantity fields
    public CartItem(Long id, Timestamp recordCreatedOn) {
        this.id = id;
        this.recordCreatedOn = recordCreatedOn;
    }

    // Getters and Setters

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

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    // toString method to provide a formatted string representation of the CartItem object
    @Override
    public String toString() {
        return "Cart Item\n----------------------\n\tID: " + id + "\n\t" + customer + "\n\tQuantity: " + quantity
                + "\n\tCondition: " + conditions + "\n\tRecord Created On: " + recordCreatedOn + "\n\tBook: " + book
                + "\n";
    }
}










































//package com.bookstore.entity;
//
//import java.math.BigDecimal;
//import java.sql.Timestamp;
//
//import javax.persistence.*;
//
//@Entity
//public class CartItem {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    
//    
//    @Column(name = "quantity", nullable = false)
//    private Integer quantity;
//
//    @Column(name = "conditions", nullable = false)
//    private String conditions = "New";
//
//    
//	@Column(name = "recordCreatedOn", nullable = false)
//    private Timestamp recordCreatedOn;
//
//	
//    @ManyToOne
//    @JoinColumn(name = "bookID", referencedColumnName = "bookID")
//    private Book book;
//
//    @ManyToOne
//    @JoinColumn(name = "customerID", referencedColumnName = "customerID")
//    private Customer customer;
//    
//
//    public CartItem(Long id, Integer quantity, String conditions, Timestamp recordCreatedOn, Book book,
//			Customer customer) {
//		this.id = id;
//		this.quantity = quantity;
//		this.conditions = conditions;
//		this.recordCreatedOn = recordCreatedOn;
//		this.book = book;
//		this.customer = customer;
//	}
//
//
//	public CartItem(Long id, Integer quantity, String conditions, Timestamp recordCreatedOn, Book book) {
//		this.id = id;
//		this.quantity = quantity;
//		this.conditions = conditions;
//		this.recordCreatedOn = recordCreatedOn;
//		this.book = book;
//	}
//
//
//	public CartItem() {
//	}
//
//
//	public CartItem(Long id, Integer quantity, String conditions, BigDecimal priceOfUnitQuantity, Timestamp recordCreatedOn) {
//		this.id = id;
//		this.quantity = quantity;
//		this.conditions = conditions;
//		this.recordCreatedOn = recordCreatedOn;
//	}
//
//
//	public Customer getCustomer() {
//		return customer;
//	}
//
//
//	public void setCustomer(Customer customer) {
//		this.customer = customer;
//	}
//	
//	
//	
//
//
//	public Long getId() {
//		return id;
//	}
//
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//
//	public Integer getQuantity() {
//		return quantity;
//	}
//
//
//	public void setQuantity(Integer quantity) {
//		this.quantity = quantity;
//	}
//
//	public Timestamp getRecordCreatedOn() {
//		return recordCreatedOn;
//	}
//
//
//	public void setRecordCreatedOn(Timestamp recordCreatedOn) {
//		this.recordCreatedOn = recordCreatedOn;
//	}
//
//
//	public Book getBook() {
//		return book;
//	}
//
//
//	public void setBook(Book book) {
//		this.book = book;
//	}
//	
//	public String getConditions() {
//		return conditions;
//	}
//
//
//	public void setConditions(String conditions) {
//		this.conditions = conditions;
//	}
//
//
//	@Override
//	public String toString() {
//		return "Cart Item\n----------------------\n\tID: " + id +"\n\t" +customer + "\n\tQuantity: " + quantity + "\n\tCondition: " + conditions + "\n\tRecord Created On: " + recordCreatedOn + "\n\tBook: " + book + "\n";
//	}
//    
//    
//    
//
//	
//	
//    
//    
//}
