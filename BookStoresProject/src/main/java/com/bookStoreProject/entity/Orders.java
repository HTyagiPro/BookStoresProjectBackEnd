package com.bookStoreProject.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "Orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderID")
    private Long orderID;

    @ManyToOne
    @JoinColumn(name = "customerID", referencedColumnName = "customerID")
    private Customer customer;

    @Column(name = "orderDate", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "totalAmount", nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "shippingAddress", nullable = false)
    private String shippingAddress;

    @Column(name = "taxAmount", nullable = false)
    private BigDecimal taxAmount;

    @Column(name = "discountAmount", nullable = false)
    private BigDecimal discountAmount;

    @Column(name = "recordCreatedOn", nullable = false)
    private Timestamp recordCreatedOn = new Timestamp(new Date().getTime());

    // Constructors
	public Orders() {
	}

	public Orders(Long orderID, Customer customer, LocalDateTime orderDate, BigDecimal totalAmount,
			String shippingAddress, BigDecimal taxAmount, BigDecimal discountAmount, Timestamp recordCreatedOn) {
		this.orderID = orderID;
		this.customer = customer;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.shippingAddress = shippingAddress;
		this.taxAmount = taxAmount;
		this.discountAmount = discountAmount;
		this.recordCreatedOn = recordCreatedOn;
	}

	public Orders(Long orderID, LocalDateTime orderDate, BigDecimal totalAmount, String shippingAddress,
			BigDecimal taxAmount, BigDecimal discountAmount, Timestamp recordCreatedOn) {
		this.orderID = orderID;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.shippingAddress = shippingAddress;
		this.taxAmount = taxAmount;
		this.discountAmount = discountAmount;
		this.recordCreatedOn = recordCreatedOn;
	}
	
	//getters, and setters
	
	public Long getOrderID() {
		return orderID;
	}

	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Timestamp getRecordCreatedOn() {
		return recordCreatedOn;
	}

	public void setRecordCreatedOn(Timestamp recordCreatedOn) {
		this.recordCreatedOn = recordCreatedOn;
	}

	
	
	@Override
	public String toString() {
		return "Orders:\n-----------------------------------\n Order ID: " + orderID + "\n\t" + customer + "\n\tOrder Date:" + orderDate + "\n\tSubtotal Amount: "
				+ totalAmount + "\n\tShipping Address: " + shippingAddress + "\n\tTax Amount: " + taxAmount
				+ "\n\tDiscount Amount: " + discountAmount + "\n\tRecord Created On: " + recordCreatedOn + "]";
	}
	
	

    
	
    
}

