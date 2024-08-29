package com.rating.model;

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
public class Actor {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long actorId;

    private String name;

    private String email;

    private String role;

    @ManyToMany(mappedBy = "actors")
    @JsonIgnore
    private Set<Movie> movies;
}
