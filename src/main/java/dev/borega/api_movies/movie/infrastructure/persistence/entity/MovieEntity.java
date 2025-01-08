package dev.borega.api_movies.movie.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.borega.api_movies.company.infrastructure.persistence.entity.CompanyEntity;
import dev.borega.api_movies.movie.domain.model.MovieGenre;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
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

    @NotNull
    private Integer runtime;

    @NotNull
    private LocalDate releaseDate;
    private String poster;

    @NotBlank
    private String classification;

    @ColumnDefault("0")
    @Generated
    @Column(insertable = false, updatable = false)
    private Double rating;

    private Set<MovieGenre> genres;

    @ManyToMany
    @JoinTable(
            name = "companies_movies",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id")
    )
    @JsonIgnore
    private Set<CompanyEntity> companies;

    @OneToMany(mappedBy = "movie")
    private Set<MoviesHasCelebs> celebs;
}
