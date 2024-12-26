package dev.borega.api_movies.company.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class Company {
    private Long id;
    private String name;
    private String country;
    private LocalDate foundedDate;
    private String website;
    private String logo;

    public boolean isValidFoundedDate() {
        return !this.foundedDate.isAfter(LocalDate.now());
    }
}
