package dev.borega.api_movies.celeb.domain.model;

import dev.borega.api_movies.movie.domain.model.Movie;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Celeb {
    private Long id;
    private String fullName;
    private LocalDate birthDate;
    private String birthPlace;
    private String biography;
    private String photo;
    private Set<Movie> movies;

    public boolean isValidBirthdate() {
        return !this.birthDate.isAfter(LocalDate.now());
    }
}
