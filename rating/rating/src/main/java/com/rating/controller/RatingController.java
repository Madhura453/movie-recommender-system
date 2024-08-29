package com.rating.controller;


import com.rating.model.Rating;
import com.rating.repository.RatingRepository;
import com.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/give")
    public ResponseEntity<?> giveRating(@RequestBody Rating rating) {

        return ResponseEntity.ok(ratingService.giveRating(rating));
    }

    @GetMapping("/movie/{movieId}")
    public List<Rating> getMovieRatings(@PathVariable Long movieId) {
        return ratingService.getMovieRatings(movieId);
    }

    @GetMapping("get/movie/{movieId}/userId/{userId}")
    public Rating getMovieAndUserRatings(@PathVariable Long movieId,
                                               @PathVariable Long userId) {
        return ratingService.getMovieAndUserRatings(movieId, userId);
    }

    @DeleteMapping("delete/movie/{movieId}/userId/{userId}")
    public void deleteMovieRating(@PathVariable Long movieId, @PathVariable Long userId) {
        ratingService.deleteMovieRating(movieId,userId);
    }


}
