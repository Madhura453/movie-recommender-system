package com.movie.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Movie {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long movieId;

    private String title;

    private String description;

    private String director;

    private String image;

    private String videoUrl;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name="movie_id"),
    inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<Actor> actors;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name="movie_id"),
    inverseJoinColumns = @JoinColumn(name="genres_id"))
    private List<Genres> genresList;

    @OneToMany(mappedBy = "movie")
    private List<Rating> ratings;

}
