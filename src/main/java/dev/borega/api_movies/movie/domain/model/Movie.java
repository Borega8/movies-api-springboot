package dev.borega.api_movies.movie.domain.model;

import dev.borega.api_movies.celeb.domain.model.Celeb;
import dev.borega.api_movies.company.domain.model.CompanyInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class Movie {
    private Long id;
    private String title;
    private String synopsis;
    private Integer runtime;
    private LocalDate releaseDate;
    private String poster;
    private MPAClassification classification;
    private Double rating;
    private Set<MovieGenre> genres;
    private Set<CompanyInfo> companies;
    private Set<Celeb> celebs;

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
