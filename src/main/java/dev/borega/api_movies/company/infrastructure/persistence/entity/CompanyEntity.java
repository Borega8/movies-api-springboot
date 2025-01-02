package dev.borega.api_movies.company.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.borega.api_movies.movie.infrastructure.persistence.entity.MovieEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "companies")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;
    private String country;

    @NotNull
    private LocalDate foundedDate;
    private String website;
    private String logo;

    @ManyToMany(mappedBy = "companies")
    @JsonIgnore
    private Set<MovieEntity> movies;
}
