package com.recommendation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Actor {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long actorId;

    private String name;

    private String email;

    private String role;

    public Actor(String name, String role) {
        this.name=name;
        this.role = role;
    }

    @ManyToMany(mappedBy = "actors")
    @JsonIgnore
    private Set<Movie> movies;
}
