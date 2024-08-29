package com.recommendation.repository;


import com.recommendation.model.Rating;
import com.recommendation.model.RatingId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, RatingId> {

    List<Rating> findByRatingIdMovieId(Long movieId);
}
