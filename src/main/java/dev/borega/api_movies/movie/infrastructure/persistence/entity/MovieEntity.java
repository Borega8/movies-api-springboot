package dev.borega.api_movies.movie.infrastructure.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Entity
@Table(name = "movies")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @Column(length = 2000)
    private String synopsis;

    @NotBlank
    private Time duration;

    @NotBlank
    private LocalDate releaseDate;
    private String poster;

    @NotBlank
    private String classification;

    private Double rating;
}
