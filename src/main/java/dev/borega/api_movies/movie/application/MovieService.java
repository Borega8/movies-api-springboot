package dev.borega.api_movies.movie.application;

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
        return movieRepository.save(movie);
    }

    @Override
    public Movie update(Movie movie) {
        return movieRepository.update(movie);
    }

    @Override
    public void delete(Long id) {
        movieRepository.delete(id);
    }
}
