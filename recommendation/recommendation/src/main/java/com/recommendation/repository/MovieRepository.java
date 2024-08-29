package com.recommendation.repository;


import com.recommendation.constants.SqlQueryConstant;
import com.recommendation.model.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Long> {

    @Query(value= SqlQueryConstant.RECOMMENDATION_QUERY,nativeQuery = true)
   List<Movie> getRecommendationsBasedOnSelectedMovie(@Param("movieId") Long movieId,
                                                      @Param("directorName") String directorName,
                                                      @Param("description") String description,
                                                      @Param("genresNames") List<String> genresNames,
                                                      @Param("actorNames") List<String> actorNames,
                                                      @Param("userId") Long userId,
                                                      Pageable pageable);

    @Query(value= SqlQueryConstant.USER_LIKED_QUERY,nativeQuery = true)
    List<String> getRecommendationsBasedOnOtherUserLikedVideos(@Param("movieId") Long movieId,
                                                       @Param("userId") Long userId);

    @Query(value = "select * from movie where movie_id in (:movieIds)",nativeQuery = true)
    List<Movie> findMovies(@Param("movieIds") List<Long> movieIds, Pageable pageable);

}
