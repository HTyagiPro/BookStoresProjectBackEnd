package com.example.bookStoreProject.services;

import com.example.bookStoreProject.entity.Rating;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RatingService {

    List<Rating> getAllRatings();

    Optional<Rating> getRatingById(Long id);

    Rating saveRating(Rating rating);

    void deleteRating(Long id);
    
    void rate(Map<String, String> map);
}

