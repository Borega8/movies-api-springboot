package dev.borega.api_movies.movie.infrastructure.persistence.entity;

import dev.borega.api_movies.celeb.domain.model.CelebRole;
import dev.borega.api_movies.celeb.infrastructure.persistence.entity.CelebEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class MoviesHasCelebs {
    @EmbeddedId
    MoviesHasCelebsKey id;

    @ManyToOne
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    MovieEntity movie;

    @ManyToOne
    @MapsId("celebId")
    @JoinColumn(name = "celeb_id")
    CelebEntity celeb;

    List<CelebRole> role;
}
