package dev.borega.api_movies.movie.application;

import dev.borega.api_movies.movie.domain.model.MPAClassification;
import dev.borega.api_movies.movie.domain.model.Movie;

import java.util.List;

public interface MoviePort {
    List<Movie> getAll();

    Movie getById(Long id);

    List<Movie> getByTitle(String title);

    List<Movie> getByClassification(MPAClassification classification);

    List<Movie> getByTitleAndClassification(String title, MPAClassification classification);

    Movie save(Movie movie);

    Movie update(Movie movie);

    void delete(Long id);
}
