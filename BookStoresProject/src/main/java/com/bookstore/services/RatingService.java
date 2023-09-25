package com.bookstore.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.bookstore.entity.Rating;

public interface RatingService {

    List<Rating> getAllRatings();

    Optional<Rating> getRatingById(Long id);

    Rating saveRating(Rating rating);

    void deleteRating(Long id);
    
    ResponseEntity<String> rate(Map<String, String> map);
    
    public ResponseEntity<Double> getRating(Map<String, String> map);
    
    public ResponseEntity<String> setRating(Map<String, String> map);
}

