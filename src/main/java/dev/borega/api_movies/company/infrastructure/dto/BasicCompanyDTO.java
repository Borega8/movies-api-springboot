package dev.borega.api_movies.company.infrastructure.dto;

import dev.borega.api_movies.movie.infrastructure.dto.MovieInfo;

import java.time.LocalDate;
import java.util.Set;

public record BasicCompanyDTO(
        String name,
        String country,
        LocalDate foundedDate,
        String website,
        String logo,
        Set<MovieInfo> movies
) {
}
