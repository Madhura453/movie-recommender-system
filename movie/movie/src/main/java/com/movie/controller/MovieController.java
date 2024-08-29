package com.movie.controller;

import com.movie.model.Movie;
import com.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/add")
   // @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> addMovie(@RequestBody Movie movie)
    {
        return new ResponseEntity(movieService.addMovie(movie), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateMovie(@RequestBody Movie movie,
                                         @RequestParam(name="movieID") Long movieId)
    {
        return new ResponseEntity(movieService.addMovie(movie), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteMovie(@RequestParam(name="movieID") Long movieId)
    {
        movieService.deleteMovie(movieId);
        return new ResponseEntity("Movie deleted successfully", HttpStatus.ACCEPTED);
    }

    @GetMapping("/get/movieList")
    public List<Movie> getMovies()
    {

        return movieService.getMovieList();
    }
}
