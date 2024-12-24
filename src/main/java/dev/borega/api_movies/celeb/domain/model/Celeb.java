package dev.borega.api_movies.celeb.domain.model;

import lombok.*;

import java.time.LocalDate;

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

    public boolean isValidBirthdate() {
        return !this.birthDate.isAfter(LocalDate.now());
    }
}
