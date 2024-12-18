package dev.borega.api_movies.celeb.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@ToString
public class Celeb {
    private Long id;
    private String fullName;
    private LocalDate birthDate;
    private String birthPlace;
    private String biography;
    private String photo;
}
