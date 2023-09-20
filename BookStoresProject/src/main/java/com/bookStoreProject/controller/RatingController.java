package com.bookStoreProject.controller;

import com.bookStoreProject.entity.Rating;
import com.bookStoreProject.services.RatingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    // Get all ratings
    @GetMapping("/")
    public List<Rating> getAllRatings() {
        return ratingService.getAllRatings();
    }

    // Get a rating by ID
    @GetMapping("/{id}")
    public Optional<Rating> getRatingById(@PathVariable Long id) {
        return ratingService.getRatingById(id);
    }

    // Save a rating
    @PostMapping("/")
    public Rating saveRating(@RequestBody Rating rating) {
        return ratingService.saveRating(rating);
    }

    // Delete a rating by ID
    @DeleteMapping("/{id}")
    public void deleteRating(@PathVariable Long id) {
        ratingService.deleteRating(id);
    }
    
    // Get the average rating of a book
    @GetMapping("/getRating")
    public ResponseEntity<Double> getRating(@RequestBody(required = true) Map<String, String> map) {
        try {
            return ratingService.getRating(map);
        } catch (Exception e) {
            // Handle exceptions and log the error
            e.printStackTrace();
        }
        
        // If an exception occurs, return a not found response
        return new ResponseEntity<Double>(0.0, HttpStatus.NOT_FOUND);
    }
    
    // Set a rating in the rating table and update the average value in the book table
    @PostMapping("/setRating")
    public ResponseEntity<String> setRating(@RequestBody(required = true) Map<String, String> map) {
        try {
            return ratingService.setRating(map);
        } catch (Exception e) {
            // Handle exceptions and log the error
            e.printStackTrace();
        }
        
        // If an exception occurs, return an internal server error response
        return new ResponseEntity<String>("Something Went Wrong, Try again after Sometime...!!!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}















/*
package com.bookStoreProject.controller;

import com.bookStoreProject.entity.Rating;
import com.bookStoreProject.services.RatingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/")
    public List<Rating> getAllRatings() {
        return ratingService.getAllRatings();
    }

    @GetMapping("/{id}")
    public Optional<Rating> getRatingById(@PathVariable Long id) {
        return ratingService.getRatingById(id);
    }

    @PostMapping("/")
    public Rating saveRating(@RequestBody Rating rating) {
        return ratingService.saveRating(rating);
    }

    @DeleteMapping("/{id}")
    public void deleteRating(@PathVariable Long id) {
        ratingService.deleteRating(id);
    }
    
    //Get average rating of the book
    @GetMapping("/getRating")
    public ResponseEntity<Double> getRating(@RequestBody(required = true) Map<String, String> map){
    	try {
			return ratingService.getRating(map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return new ResponseEntity<Double>(0.0, HttpStatus.NOT_FOUND);
    }
    
    
    //Setting rating in rating table and average value in book table
    @PostMapping("/setRating")
    public ResponseEntity<String> setRating(@RequestBody(required = true) Map<String, String> map){
    	try {
			return ratingService.setRating(map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return new ResponseEntity<String>("Something Went Wrong, Try again after Sometime...!!!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    
}
*/
