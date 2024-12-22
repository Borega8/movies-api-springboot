package dev.borega.api_movies.movie.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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
    private List<MovieGenre> genres;

    public boolean isValidRuntime() {
        return this.runtime > 0;
    }

    public boolean isValidReleaseDate() {
        return !this.releaseDate.isAfter(LocalDate.now());
    }

    public boolean isValidClassification() {
        return Arrays.stream(MPAClassification.values()).anyMatch(cl -> cl.compareTo(this.classification) == 0);
    }
}
