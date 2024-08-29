package com.recommendation.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

    @EmbeddedId
    private RatingId ratingId;

    private Long totalRating;

    private Long userRating;

    private boolean visited;

    @Enumerated(EnumType.STRING)
    private LikeStatus likeStatus;

    @ManyToOne
    @JoinColumn(name = "movieId", insertable = false,updatable = false)
    //@JsonIgnore
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "userId", insertable = false,updatable = false)
   // @JsonIgnore
    private User user;
}
