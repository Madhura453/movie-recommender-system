package com.movie.repository;

import com.movie.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActorRepository extends JpaRepository<Actor,Long> {

     Optional<Actor> findByEmail(String email);
}
