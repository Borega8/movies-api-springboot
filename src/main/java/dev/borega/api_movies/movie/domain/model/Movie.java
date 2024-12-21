package dev.borega.api_movies.movie.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@ToString
public class Movie {
    private Long id;
    private String title;
    private String synopsis;
    private Integer runtime;
    private LocalDate releaseDate;
    private String poster;
    private MPAClassification classification;
    private Double rating;
}
