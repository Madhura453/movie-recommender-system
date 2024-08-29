package com.recommendation.controller;

import com.recommendation.constants.SqlQueryConstant;
import com.recommendation.model.Movie;
import com.recommendation.service.RecommendationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/recommendation")
@Slf4j
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("movies")
    @Operation(description = "get the recommended movies", summary = "get the recommended movies based on " +
            "director and description , genres, actors, past watched movies")
    public List<Movie> getRecommendationsBasedOnSelectedMovie(
            @RequestParam(name = "movieId") Long movieId, @RequestParam(name = "userId") Long userId,
            @RequestParam(value = "pageNumber", required = false,
                    defaultValue = SqlQueryConstant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false,
                    defaultValue = SqlQueryConstant.DEFAULT_PAGE_SIZE) Integer pageSize) {
        log.info("getting recommendations for the movie ID : {} and user ID: {}", movieId, userId);
        // Content filtering
        return recommendationService.
                getRecommendationsBasedOnSelectedMovie(movieId, userId, pageNumber, pageSize);
    }

    @GetMapping("/liked")
    @Operation(description = "Get recommended movies based on others", summary = "Get recommended movies based on " +
            "movies that were liked the most by other users watching the same movie ")
    public List<Movie> getRecommendationsBasedOnOtherUserLikedVideos(
            @RequestParam(name = "movieId") Long movieId, @RequestParam(name = "userId") Long userId,
            @RequestParam(value = "pageNumber", required = false,
                    defaultValue = SqlQueryConstant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false,
                    defaultValue = SqlQueryConstant.DEFAULT_PAGE_SIZE) Integer pageSize) {
        /*
          collaboration filtering
         */
        log.info("getting recommendations for the movie ID : {} and user ID: {}", movieId, userId);
        return recommendationService.
                getRecommendationsBasedOnOtherUserLikedVideos(movieId, userId, pageNumber, pageSize);
    }

}
