package com.rating.service;

import com.rating.exception.RatingNotFoundException;
import com.rating.model.Rating;
import com.rating.model.RatingId;
import com.rating.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public Rating giveRating(Rating rating) {

        return ratingRepository.save(rating);
    }

    public List<Rating> getMovieRatings(Long movieId) {
       return ratingRepository.findByRatingIdMovieId(movieId);
    }

    public Rating getMovieAndUserRatings(Long movieId, Long userId) {

       Optional<Rating> rating= ratingRepository.findById(new RatingId(movieId, userId));

       if(rating.isEmpty())
       {
           throw new RatingNotFoundException(404,"Rating not found", HttpStatus.NOT_FOUND);
       }

       return rating.get();

    }

    public void deleteMovieRating(Long movieId, Long userId) {
        RatingId ratingId = new RatingId(movieId, userId);
        ratingRepository.deleteById(ratingId);
    }

}
