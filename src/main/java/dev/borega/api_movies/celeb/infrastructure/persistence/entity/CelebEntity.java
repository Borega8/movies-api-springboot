package dev.borega.api_movies.celeb.infrastructure.persistence.entity;

import dev.borega.api_movies.movie.infrastructure.persistence.entity.MoviesHasCelebs;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "celebs")
public class CelebEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long celebId;

    @NotBlank
    private String name;

    @NotNull
    @Column(name = "birthdate")
    private LocalDate birthDate;

    @NotBlank
    @Column(name = "birthplace")
    private String placeOfBirth;

    @Column(length = 2000)
    private String biography;

    private String photo;

    @OneToMany(mappedBy = "celeb")
    private Set<MoviesHasCelebs> movies;
}
