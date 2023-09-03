package com.example.bookStoreProject.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name = "OrderItems")
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orderID", referencedColumnName = "orderID")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "bookID", referencedColumnName = "bookID")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "customerID", referencedColumnName = "customerID")
    private Customer customer;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "priceOfUnitQuantity", nullable = false)
    private BigDecimal priceOfUnitQuantity;

    @Column(name = "recordCreatedOn", nullable = false)
    private Timestamp recordCreatedOn;

    //constructor
	public OrderItems() {
	}

	public OrderItems(Long id, Orders order, Book book, Customer customer, Integer quantity,
			BigDecimal priceOfUnitQuantity, Timestamp recordCreatedOn) {
		this.id = id;
		this.order = order;
		this.book = book;
		this.customer = customer;
		this.quantity = quantity;
		this.priceOfUnitQuantity = priceOfUnitQuantity;
		this.recordCreatedOn = recordCreatedOn;
	}

	
	
	
	
	public OrderItems(Long id, Integer quantity, BigDecimal priceOfUnitQuantity, Timestamp recordCreatedOn) {
		this.id = id;
		this.quantity = quantity;
		this.priceOfUnitQuantity = priceOfUnitQuantity;
		this.recordCreatedOn = recordCreatedOn;
	}

	//getters, and setters
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
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

	@Override
	public String toString() {
		return "\nOrder Items:\n-------------------------------------\n\tID:" + id + "\n" + order + "\n" + book + "\n" + customer + "\n\tQuantity: "
				+ quantity + "\n\tPrice Of Unit Quantity: " + priceOfUnitQuantity + "\n\tRecord Created On: " + recordCreatedOn
				+ "\n";
	}
	
	

    
    
    
    
}

