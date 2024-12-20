package dev.borega.api_movies.movie.domain.repository;

import dev.borega.api_movies.movie.domain.model.MPAClassification;
import dev.borega.api_movies.movie.domain.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieRepository {
    List<Movie> getAll();

    Optional<Movie> getById(Long id);

    List<Movie> getByTitle(String title);

    List<Movie> getByClassification(MPAClassification classification);

    List<Movie> getByTitleAndClassification(String title, MPAClassification classification);

    Movie save(Movie movie);

    Movie update(Movie movie);

    void delete(Long id);

}
