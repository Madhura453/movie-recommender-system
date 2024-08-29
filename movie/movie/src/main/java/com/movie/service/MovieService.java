package com.movie.service;

import com.movie.exception.MovieNotFoundException;
import com.movie.model.Actor;
import com.movie.model.Genres;
import com.movie.model.Movie;
import com.movie.repository.ActorRepository;
import com.movie.repository.GenresRepository;
import com.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private GenresRepository genresRepository;

    public Movie addMovie(Movie movie) {

        List<Actor> actorList = movie.getActors();

        List<Genres> genresList = movie.getGenresList();

        actorRepository.saveAll(actorList);
        genresRepository.saveAll(genresList);


//        actorList.forEach(actor -> {
//                    if (actorRepository.findByEmail(actor.getEmail()).isEmpty()) {
//                        actorRepository.save(actor);
//                    }
//                }
//        );
//
//        genresList.forEach(genres -> {
//            if (genresRepository.findByName(genres.getName()).isEmpty()) {
//                genresRepository.save(genres);
//            }
//        });

        Movie addMovie = movieRepository.save(movie);

        return movieRepository.findById(addMovie.getMovieId()).orElse(null);
    }

    public void deleteMovie(Long movieId) {

        if(movieRepository.findById(movieId).isEmpty())
        {
            throw new MovieNotFoundException(404,"Movie not exist!", HttpStatus.NOT_FOUND);
        }

        movieRepository.deleteById(movieId);
    }

    public List<Movie> getMovieList() {

        return movieRepository.findAll();
    }
}
