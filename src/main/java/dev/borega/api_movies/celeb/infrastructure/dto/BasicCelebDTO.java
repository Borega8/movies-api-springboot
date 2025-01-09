package dev.borega.api_movies.celeb.infrastructure.dto;

import java.time.LocalDate;
import java.util.Set;

public record BasicCelebDTO(
        String fullName,
        LocalDate birthDate,
        String birthPlace,
        String biography,
        String photo,
        Set<CelebMovieDTO> movies
) {
}
