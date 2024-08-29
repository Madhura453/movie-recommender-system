package com.movie.repository;

import com.movie.model.Genres;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenresRepository extends JpaRepository<Genres,Long> {

    Optional<Genres> findByName(String name);
}
