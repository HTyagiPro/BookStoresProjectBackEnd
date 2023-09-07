package com.example.bookStoreProject.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "Payments")
public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentID")
    private Long paymentID;

    @ManyToOne
    @JoinColumn(name = "orderID", referencedColumnName = "orderID")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "customerID", referencedColumnName = "customerID")
    private Customer customer;

    @Column(name = "paymentDate", nullable = false)
    private LocalDateTime paymentDate;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "recordCreatedOn", nullable = false)
    private Timestamp recordCreatedOn = new Timestamp(new Date().getTime());
    // Constructors
	
    public Payments() {
	}

	public Payments(Long paymentID, Orders order, Customer customer, LocalDateTime paymentDate, BigDecimal amount,
			String status, Timestamp recordCreatedOn) {
		this.paymentID = paymentID;
		this.order = order;
		this.customer = customer;
		this.paymentDate = paymentDate;
		this.amount = amount;
		this.status = status;
		this.recordCreatedOn = recordCreatedOn;
	}
	

	public Payments(Long paymentID, LocalDateTime paymentDate, BigDecimal amount, String status,
			Timestamp recordCreatedOn) {
		this.paymentID = paymentID;
		this.paymentDate = paymentDate;
		this.amount = amount;
		this.status = status;
		this.recordCreatedOn = recordCreatedOn;
	}
    
    //getters, and setters
	
	public Long getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(Long paymentID) {
		this.paymentID = paymentID;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		return "Payments:\n--------------------------\nPayment ID: " + paymentID + "\n\t" + order + "\n\t" + customer + "\nPayment Date: "
				+ paymentDate + "\n\tAmount: " + amount + "\n\tStatus: " + status + "\n\tRecord Created On: " + recordCreatedOn
				+ "\n";
	}
	
	
}

