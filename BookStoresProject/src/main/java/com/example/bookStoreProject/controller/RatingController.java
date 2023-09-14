package com.example.bookStoreProject.controller;

import com.example.bookStoreProject.entity.Rating;
import com.example.bookStoreProject.services.RatingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
}
