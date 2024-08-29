package com.recommendation.service;

import com.recommendation.RecommendationApplication;
import com.recommendation.model.Actor;
import com.recommendation.model.Genres;
import com.recommendation.model.Movie;
import com.recommendation.repository.MovieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class RecommendationServiceTest {

    @MockBean
    private MovieRepository movieRepository;

    @Autowired
    private RecommendationService recommendationService;

    @Test
    public void testGetRecommendationsBasedOnSelectedMovie()
    {
        Movie movie = new Movie();
        movie.setMovieId(1L);
        movie.setDescription("movie about comedy");
        movie.setDirector("viswanth");
        movie.setGenresList(List.of(new Genres(1L,"comedy"), new Genres(2L,"horror")));
        movie.setActors(List.of(new Actor("charly","Hero"), new Actor("savathri","Heroine")));
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
        List<Movie> movieList = new ArrayList<>();
        Movie movie1 = new Movie();
        movie1.setMovieId(2L);
        movie1.setDescription("horror moive");
        movie1.setDirector("viswanth");
        movieList.add(movie1);
        when(movieRepository.getRecommendationsBasedOnSelectedMovie(1L,
                movie.getDirector(),movie.getDescription(),
                List.of("comedy","horror"), List.of("charly", "savathri"), 152L, PageRequest.of(0,1)))
                .thenReturn(movieList);
        List<Movie> movies = recommendationService.getRecommendationsBasedOnSelectedMovie
                (1L,152L,0,1);

        Assertions.assertEquals(movieList,movies);
    }
}
