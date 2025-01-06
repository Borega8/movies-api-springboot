package dev.borega.api_movies.movie.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class MoviesHasCelebsKey implements Serializable {
    @Column(name = "movie_id")
    Long movieId;
    @Column(name = "celeb_id")
    Long celebId;
}
