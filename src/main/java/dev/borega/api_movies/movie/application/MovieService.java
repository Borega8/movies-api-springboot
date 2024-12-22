package dev.borega.api_movies.movie.application;

import dev.borega.api_movies.movie.domain.exception.InvalidValueException;
import dev.borega.api_movies.movie.domain.exception.MovieNotFoundException;
import dev.borega.api_movies.movie.domain.model.MPAClassification;
import dev.borega.api_movies.movie.domain.model.Movie;
import dev.borega.api_movies.movie.domain.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieService implements MoviePort {

    private final MovieRepository movieRepository;

    @Override
    public List<Movie> getAll() {
        return movieRepository.getAll();
    }

    @Override
    public Movie getById(Long id) {
        return movieRepository.getById(id).orElseThrow(MovieNotFoundException::new);
    }

    @Override
    public List<Movie> getByTitle(String title) {
        return movieRepository.getByTitle(title);
    }

    @Override
    public List<Movie> getByClassification(MPAClassification classification) {
        return movieRepository.getByClassification(classification);
    }

    @Override
    public List<Movie> getByTitleAndClassification(String title, MPAClassification classification) {
        return movieRepository.getByTitleAndClassification(title, classification);
    }

    @Override
    public Movie save(Movie movie) {
        validateMovie(movie);

        return movieRepository.save(movie);
    }

    @Override
    public Movie update(Movie movie) {
        validateMovie(movie);

        return movieRepository.update(movie);
    }

    @Override
    public void delete(Long id) {
        movieRepository.delete(id);
    }

    private void validateMovie(Movie movie) {
        if (!movie.isValidRuntime())
            throw new InvalidValueException("Runtime must be greater than 0");

        if (!movie.isValidReleaseDate())
            throw new InvalidValueException("Release date cannot be after today");

        if (!movie.isValidClassification())
            throw new InvalidValueException("Incorrect classification value");
    }
}
