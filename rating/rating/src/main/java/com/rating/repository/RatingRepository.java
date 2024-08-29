package com.rating.repository;

import com.rating.model.Rating;
import com.rating.model.RatingId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, RatingId> {

    List<Rating> findByRatingIdMovieId(Long movieId);
}
