package com.example.bookStoreProject.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
    private Timestamp recordCreatedOn;

	public Orders() {
	}

    // Constructors, getters, and setters
    
}

