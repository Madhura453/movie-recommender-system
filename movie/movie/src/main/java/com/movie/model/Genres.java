package com.movie.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Genres {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long genresId;

    private String name;

    @ManyToMany(mappedBy = "genresList")
    @JsonIgnore
    private Set<Movie> movies;
}
