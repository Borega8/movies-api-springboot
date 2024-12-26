package dev.borega.api_movies.movie.infrastructure.dto;

import dev.borega.api_movies.movie.domain.model.MPAClassification;
import dev.borega.api_movies.movie.domain.model.MovieGenre;

import java.time.LocalDate;
import java.util.List;

public record BasicMovieDTO(
        String title,
        String synopsis,
        Integer runtime,
        LocalDate releaseDate,
        String poster,
        MPAClassification classification,
        Double rating,
        List<MovieGenre>genres
) {
}
