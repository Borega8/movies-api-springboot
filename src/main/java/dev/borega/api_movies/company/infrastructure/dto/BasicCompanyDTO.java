package dev.borega.api_movies.company.infrastructure.dto;

import java.time.LocalDate;

public record BasicCompanyDTO(
        String name,
        String country,
        LocalDate foundedDate,
        String website,
        String logo
) {
}
