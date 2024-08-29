package com.movie.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingId implements Serializable {

    private Long movieId;

    private Long userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RatingId ratingId)) return false;
        return Objects.equals(movieId, ratingId.movieId) && Objects.equals(userId, ratingId.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, userId);
    }
}
