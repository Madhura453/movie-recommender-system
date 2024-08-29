package com.recommendation.service;

import com.recommendation.constants.Constants;
import com.recommendation.exception.MovieNotFoundException;
import com.recommendation.model.Actor;
import com.recommendation.model.Genres;
import com.recommendation.model.Movie;
import com.recommendation.model.Rating;
import com.recommendation.model.RatingId;
import com.recommendation.repository.MovieRepository;
import com.recommendation.repository.RatingRepository;
import com.recommendation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class RecommendationService {


    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RatingRepository ratingRepository;

    public List<Movie> getRecommendationsBasedOnSelectedMovie(Long movieId, Long userId,
                                                              Integer pageNumber, Integer pageSize )
    {
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        if(optionalMovie.isEmpty())
        {
            throw new MovieNotFoundException(Constants.NOT_FOUND, Constants.MOVIE_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        saveUserVisitedMovie(movieId, userId);
        Movie movie = optionalMovie.get();
        List<String> genresNames = movie.getGenresList()
                .stream().map(Genres::getName).toList();
        List<String> majorRoles = List.of("Hero","Heroine","vilan","mother","father","major role");
        List<String> actorNames = movie.getActors().stream()
                .filter(actor -> majorRoles.contains(actor.getRole()))
                .map(Actor::getName).toList();

        return movieRepository.getRecommendationsBasedOnSelectedMovie(movieId,movie.getDirector(),
                movie.getDescription(),
                genresNames, actorNames, userId, PageRequest.of(pageNumber,pageSize));
    }

    public List<Movie> getRecommendationsBasedOnOtherUserLikedVideos(Long movieId, Long userId,
                                                                     Integer pageNumber, Integer pageSize)
    {
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        if(optionalMovie.isEmpty())
        {
            throw new MovieNotFoundException(Constants.NOT_FOUND, Constants.MOVIE_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        saveUserVisitedMovie(movieId, userId);
        List<String> groupMovieIds = movieRepository.getRecommendationsBasedOnOtherUserLikedVideos(movieId,userId);
        List<Long> movieIds = groupMovieIds.stream().flatMap(m->Arrays.stream(m.split(",")))
                 .map(Long::parseLong)
                .filter(r->r!=movieId)
                .toList();
        return movieRepository.findMovies(movieIds, PageRequest.of(pageNumber,pageSize));
    }



    public void saveUserVisitedMovie(Long movieId, Long userId) {

        RatingId ratingId = new RatingId(movieId, userId);
        Optional<Rating> rating = ratingRepository.findById(ratingId);

        if(rating.isEmpty())
        {
            Rating  markAsVisited = new Rating();
            markAsVisited.setRatingId(ratingId);
            markAsVisited.setVisited(true);
            ratingRepository.save(markAsVisited);
        }
    }
}
