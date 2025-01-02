package dev.borega.api_movies.movie.infrastructure.persistence.adapter;

import dev.borega.api_movies.movie.domain.model.MPAClassification;
import dev.borega.api_movies.movie.domain.model.Movie;
import dev.borega.api_movies.movie.domain.repository.MovieRepository;
import dev.borega.api_movies.movie.infrastructure.mapper.MovieMapper;
import dev.borega.api_movies.movie.infrastructure.persistence.entity.MovieEntity;
import dev.borega.api_movies.movie.infrastructure.persistence.repository.MovieDBRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class MovieAdapter implements MovieRepository {

    private final MovieDBRepository movieDBRepository;
    private final MovieMapper movieMapper = MovieMapper.INSTANCE;

    @Override
    public List<Movie> getAll() {
        return movieDBRepository.findAll().stream().map(movieMapper::movieEntityToMovie).toList();
    }

    @Override
    public Optional<Movie> getById(Long id) {
        return movieDBRepository.findById(id).map(movieMapper::movieEntityToMovie);
    }

    @Override
    public List<Movie> getByTitle(String title) {
        return movieDBRepository.findByTitleContainingIgnoreCase(title).stream().map(movieMapper::movieEntityToMovie).toList();
    }

    @Override
    public List<Movie> getByClassification(MPAClassification classification) {
        return movieDBRepository.findByClassificationIsIgnoreCase(classification.name().replace("_", "-")).stream().map(movieMapper::movieEntityToMovie).toList();
    }

    @Override
    public Movie save(Movie movie) {
        MovieEntity movieEntity = movieDBRepository.save(movieMapper.movieToMovieEntity(movie));
        return movieMapper.movieEntityToMovie(movieEntity);
    }

    @Override
    public Movie update(Movie movie) {
        MovieEntity movieEntity = movieDBRepository.save(movieMapper.movieToMovieEntity(movie));
        return movieMapper.movieEntityToMovie(movieEntity);
    }

    @Override
    public void delete(Long id) {
        movieDBRepository.deleteById(id);
    }

    @Override
    public List<Movie> getByTitleAndClassification(String title, MPAClassification classification) {
        return movieDBRepository.findByTitleContainingIgnoreCaseAndClassificationContaining(title, classification.name().replace("_", "-")).stream().map(movieMapper::movieEntityToMovie).toList();
    }
}
