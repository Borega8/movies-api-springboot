package dev.borega.api_movies.celeb.domain.repository;

import dev.borega.api_movies.celeb.domain.model.Celeb;

import java.util.List;
import java.util.Optional;

public interface CelebRepository {
    List<Celeb> getAll();
    Optional<Celeb> getById(Long id);
    Celeb save(Celeb celeb);
    Celeb update(Celeb celeb);
    void delete(Celeb celeb);
}
