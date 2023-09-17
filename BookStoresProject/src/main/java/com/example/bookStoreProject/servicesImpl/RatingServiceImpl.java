package com.example.bookStoreProject.servicesImpl;


import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.bookStoreProject.entity.Book;
import com.example.bookStoreProject.entity.Customer;
import com.example.bookStoreProject.entity.Rating;
import com.example.bookStoreProject.entity.Users;
import com.example.bookStoreProject.jwt.MyUserDetailsService;
import com.example.bookStoreProject.repository.BookRepository;
import com.example.bookStoreProject.repository.CustomerRepository;
import com.example.bookStoreProject.repository.RatingRepository;
import com.example.bookStoreProject.services.RatingService;

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

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public Optional<Rating> getRatingById(Long id) {
        return ratingRepository.findById(id);
    }

    @Override
    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public void deleteRating(Long id) {
        ratingRepository.deleteById(id);
    }

	@Override
	public ResponseEntity<String> rate(Map<String, String> map) {
		// TODO Auto-generated method stub
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Something Went Wrong, Try After Sometime...", HttpStatus.INTERNAL_SERVER_ERROR);
		
		
	}

	@Override
	public ResponseEntity<Double> getRating(Map<String, String> map) {
		// TODO Auto-generated method stub
		try {
			return new ResponseEntity<Double>(ratingRepository.getBookRatingByBookID(Long.parseLong(map.get("bookID"))), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Double>(0.0, HttpStatus.NO_CONTENT);
	}
	
	@Override
	public ResponseEntity<String> setRating(Map<String, String> map) {
		// TODO Auto-generated method stub
		try {
			Users user = myUserDetailsService.getUserDetails();
			Customer customer = customerRepository.getCustomerByEmail(user.getEmail());
			Rating rating = ratingRepository.getBookRatingByBookIDAndCustomerID(Long.parseLong(map.get("bookID")), customer.getCustomerID());
			
			System.out.println(rating);
			if(Objects.isNull(rating)) {
				rating = new Rating();
				rating.setCustomer(customer);
				rating.setBook(bookRepository.findById(Long.parseLong(map.get("bookID"))).get());
				rating.setRatingValue(Double.parseDouble(map.get("review")));
			}else {
				rating.setRatingValue(Double.parseDouble(map.get("review")));
			}
			ratingRepository.save(rating);
			Book book = bookRepository.findById(Long.parseLong(map.get("bookID"))).get();
			book.setRating(ratingRepository.getBookRatingByBookID(Long.parseLong(map.get("bookID"))));
			bookRepository.save(book);
			
			return new ResponseEntity<String>("Rating Submitted!!!", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Something Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

