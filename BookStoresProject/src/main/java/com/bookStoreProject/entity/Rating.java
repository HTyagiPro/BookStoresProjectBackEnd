package com.bookStoreProject.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customerId")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "bookId")
    private Book book;

    @Column(name = "rating")
    private double ratingValue;

    // Constructors, getters, and setters (omitted for brevity)

    public Rating() {
    }

    public Rating(Book book, double ratingValue) {
        this.book = book;
        this.ratingValue = ratingValue;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public double getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(double ratingValue) {
        this.ratingValue = ratingValue;
    }    
    
    public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return ratingValue == rating.ratingValue &&
                Objects.equals(id, rating.id) &&
                Objects.equals(book, rating.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book, ratingValue);
    }

	@Override
	public String toString() {
		return "Rating\nId: " + id + customer + book + "\nRating Value: " + ratingValue;
	}

    
}




/*
 * CREATE TABLE Rating (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    book_id INT NOT NULL,
    rating INT NOT NULL Default(0),
    Constraint fk_customerRating FOREIGN KEY (customer_id) REFERENCES Customer(customerId),
    Constraint fk_bookRating FOREIGN KEY (book_id) REFERENCES Books(bookId)
);
 * 
 * */











