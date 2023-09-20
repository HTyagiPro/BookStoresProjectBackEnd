package com.bookStoreProject.servicesImpl;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookStoreProject.entity.Book;
import com.bookStoreProject.entity.Customer;
import com.bookStoreProject.entity.Rating;
import com.bookStoreProject.entity.Users;
import com.bookStoreProject.jwt.MyUserDetailsService;
import com.bookStoreProject.repository.BookRepository;
import com.bookStoreProject.repository.CustomerRepository;
import com.bookStoreProject.repository.RatingRepository;
import com.bookStoreProject.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    
    @Autowired
    MyUserDetailsService myUserDetailsService;
    
    @Autowired
    CustomerRepository customerRepository;
    
    @Autowired
    BookRepository bookRepository;
    
    

    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    // Retrieve a list of all ratings
    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    // Retrieve a rating by its ID
    @Override
    public Optional<Rating> getRatingById(Long id) {
        return ratingRepository.findById(id);
    }

    // Save a new rating
    @Override
    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    // Delete a rating by its ID
    @Override
    public void deleteRating(Long id) {
        ratingRepository.deleteById(id);
    }

    // Method to handle rating a book
    @Override
    public ResponseEntity<String> rate(Map<String, String> map) {
        // Add your logic to handle rating a book here
        try {
            // Your implementation logic here
            
        } catch (Exception e) {
            // Handle any exceptions that may occur
            e.printStackTrace();
        }
        return new ResponseEntity<String>("Something Went Wrong, Try After Sometime...", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Method to get the average rating of a book
    @Override
    public ResponseEntity<Double> getRating(Map<String, String> map) {
        // Add your logic to retrieve the average rating of a book here
        try {
            return new ResponseEntity<Double>(ratingRepository.getBookRatingByBookID(Long.parseLong(map.get("bookID"))), HttpStatus.OK);
        } catch (Exception e) {
            // Handle any exceptions that may occur
            e.printStackTrace();
        }
        return new ResponseEntity<Double>(0.0, HttpStatus.NO_CONTENT);
    }

    // Method to set a rating for a book
    @Override
    public ResponseEntity<String> setRating(Map<String, String> map) {
        // Add your logic to set a rating for a book here
        try {
            Users user = myUserDetailsService.getUserDetails();
            Customer customer = customerRepository.getCustomerByEmail(user.getEmail());
            Rating rating = ratingRepository.getBookRatingByBookIDAndCustomerID(Long.parseLong(map.get("bookID")), customer.getCustomerID());
            
            // Check if the rating for the book by the customer already exists
            if (Objects.isNull(rating)) {
                rating = new Rating();
                rating.setCustomer(customer);
                rating.setBook(bookRepository.findById(Long.parseLong(map.get("bookID"))).get());
                rating.setRatingValue(Double.parseDouble(map.get("review")));
            } else {
                rating.setRatingValue(Double.parseDouble(map.get("review")));
            }
            ratingRepository.save(rating);
            
            // Update the book's overall rating
            Book book = bookRepository.findById(Long.parseLong(map.get("bookID"))).get();
            book.setRating(ratingRepository.getBookRatingByBookID(Long.parseLong(map.get("bookID"))));
            bookRepository.save(book);
            
            return new ResponseEntity<String>("Rating Submitted!!!", HttpStatus.OK);
        } catch (Exception e) {
            // Handle any exceptions that may occur
            e.printStackTrace();
        }
        return new ResponseEntity<String>("Something Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


























//package com.bookStoreProject.servicesImpl;
//
//
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import com.bookStoreProject.entity.Book;
//import com.bookStoreProject.entity.Customer;
//import com.bookStoreProject.entity.Rating;
//import com.bookStoreProject.entity.Users;
//import com.bookStoreProject.jwt.MyUserDetailsService;
//import com.bookStoreProject.repository.BookRepository;
//import com.bookStoreProject.repository.CustomerRepository;
//import com.bookStoreProject.repository.RatingRepository;
//import com.bookStoreProject.services.RatingService;
//
//@Service
//public class RatingServiceImpl implements RatingService {
//
//    private final RatingRepository ratingRepository;
//    
//    @Autowired
//    MyUserDetailsService myUserDetailsService;
//    
//    @Autowired
//    CustomerRepository customerRepository;
//    
//    @Autowired
//    BookRepository bookRepository;
//    
//    
//
//    @Autowired
//    public RatingServiceImpl(RatingRepository ratingRepository) {
//        this.ratingRepository = ratingRepository;
//    }
//
//    @Override
//    public List<Rating> getAllRatings() {
//        return ratingRepository.findAll();
//    }
//
//    @Override
//    public Optional<Rating> getRatingById(Long id) {
//        return ratingRepository.findById(id);
//    }
//
//    @Override
//    public Rating saveRating(Rating rating) {
//        return ratingRepository.save(rating);
//    }
//
//    @Override
//    public void deleteRating(Long id) {
//        ratingRepository.deleteById(id);
//    }
//
//	@Override
//	public ResponseEntity<String> rate(Map<String, String> map) {
//		// TODO Auto-generated method stub
//		try {
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return new ResponseEntity<String>("Something Went Wrong, Try After Sometime...", HttpStatus.INTERNAL_SERVER_ERROR);
//		
//		
//	}
//
//	@Override
//	public ResponseEntity<Double> getRating(Map<String, String> map) {
//		// TODO Auto-generated method stub
//		try {
//			return new ResponseEntity<Double>(ratingRepository.getBookRatingByBookID(Long.parseLong(map.get("bookID"))), HttpStatus.OK);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return new ResponseEntity<Double>(0.0, HttpStatus.NO_CONTENT);
//	}
//	
//	@Override
//	public ResponseEntity<String> setRating(Map<String, String> map) {
//		// TODO Auto-generated method stub
//		try {
//			Users user = myUserDetailsService.getUserDetails();
//			Customer customer = customerRepository.getCustomerByEmail(user.getEmail());
//			Rating rating = ratingRepository.getBookRatingByBookIDAndCustomerID(Long.parseLong(map.get("bookID")), customer.getCustomerID());
//			
//			System.out.println(rating);
//			if(Objects.isNull(rating)) {
//				rating = new Rating();
//				rating.setCustomer(customer);
//				rating.setBook(bookRepository.findById(Long.parseLong(map.get("bookID"))).get());
//				rating.setRatingValue(Double.parseDouble(map.get("review")));
//			}else {
//				rating.setRatingValue(Double.parseDouble(map.get("review")));
//			}
//			ratingRepository.save(rating);
//			Book book = bookRepository.findById(Long.parseLong(map.get("bookID"))).get();
//			book.setRating(ratingRepository.getBookRatingByBookID(Long.parseLong(map.get("bookID"))));
//			bookRepository.save(book);
//			
//			return new ResponseEntity<String>("Rating Submitted!!!", HttpStatus.OK);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return new ResponseEntity<String>("Something Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//}
//
