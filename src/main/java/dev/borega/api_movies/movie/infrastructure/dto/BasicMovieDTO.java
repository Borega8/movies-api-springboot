package dev.borega.api_movies.movie.infrastructure.dto;

import dev.borega.api_movies.celeb.infrastructure.dto.CelebNameDTO;
import dev.borega.api_movies.company.infrastructure.dto.CompanyNameDTO;
import dev.borega.api_movies.movie.domain.model.MPAClassification;
import dev.borega.api_movies.movie.domain.model.MovieGenre;

import java.time.LocalDate;
import java.util.Set;

public record BasicMovieDTO(
        String title,
        String synopsis,
        Integer runtime,
        LocalDate releaseDate,
        String poster,
        MPAClassification classification,
        Double rating,
        Set<MovieGenre> genres,
        Set<CompanyNameDTO> companies,
        Set<CelebNameDTO> celebs
) {
}
