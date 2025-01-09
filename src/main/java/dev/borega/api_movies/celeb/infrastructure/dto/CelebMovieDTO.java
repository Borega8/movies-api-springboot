package dev.borega.api_movies.celeb.infrastructure.dto;

import java.time.LocalDate;

public record CelebMovieDTO(
        Long id,
        String title,
        LocalDate releaseDate,
        String poster,
        String rating
) {
}
