package dev.borega.api_movies.celeb.aplication;

import dev.borega.api_movies.celeb.domain.model.Celeb;

import java.util.List;

public interface CelebPort {
    List<Celeb> getAll();
    Celeb getById(Long id);
    Celeb save(Celeb celeb);
    Celeb update(Celeb celeb);
    void delete(Long id);
}
