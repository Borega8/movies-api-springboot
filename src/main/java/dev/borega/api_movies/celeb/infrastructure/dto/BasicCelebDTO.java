package dev.borega.api_movies.celeb.infrastructure.dto;

import java.time.LocalDate;

public record BasicCelebDTO(String fullName, LocalDate birthDate, String birthPlace, String biography,
                            String photo) {
}
