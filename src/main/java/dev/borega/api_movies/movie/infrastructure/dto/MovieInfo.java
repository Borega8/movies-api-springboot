package dev.borega.api_movies.movie.infrastructure.dto;

import dev.borega.api_movies.movie.domain.model.MPAClassification;
import dev.borega.api_movies.movie.domain.model.MovieGenre;

import java.time.LocalDate;
import java.util.Set;

public record MovieInfo(
        Long id,
        String title,
        String synopsis,
        Integer runtime,
        LocalDate releaseDate,
        String poster,
        MPAClassification classification,
        Double rating,
        Set<MovieGenre> genres
) {
}
