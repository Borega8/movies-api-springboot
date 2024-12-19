package dev.borega.api_movies.celeb.infrastructure.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
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
}
