package dev.borega.api_movies.movie.infrastructure.persistence.adapter;

import dev.borega.api_movies.movie.domain.model.MPAClassification;
import dev.borega.api_movies.movie.domain.model.Movie;
import dev.borega.api_movies.movie.domain.repository.MovieRepository;
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

    private Movie movieEntityToMovie(MovieEntity movieEntity) {
        return new Movie(
                movieEntity.getId(),
                movieEntity.getTitle(),
                movieEntity.getSynopsis(),
                movieEntity.getDuration(),
                movieEntity.getReleaseDate(),
                movieEntity.getPoster(),
                MPAClassification.valueOf(movieEntity.getClassification().replace("-", "_")),
                movieEntity.getRating()
        );
    }

    @Override
    public List<Movie> getAll() {
        return movieDBRepository.findAll().stream().map(this::movieEntityToMovie).toList();
    }

    @Override
    public Optional<Movie> getById(Long id) {
        return movieDBRepository.findById(id).map(this::movieEntityToMovie);
    }

    @Override
    public List<Movie> getByTitle(String title) {
        return movieDBRepository.findByTitleContainingIgnoreCase(title).stream().map(this::movieEntityToMovie).toList();
    }

    @Override
    public List<Movie> getByClassification(MPAClassification classification) {
        return movieDBRepository.findByClassificationIsIgnoreCase(classification.name().replace("_", "-")).stream().map(this::movieEntityToMovie).toList();
    }

    @Override
    public Movie save(Movie movie) {
        return null;
    }

    @Override
    public Movie update(Movie movie) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Movie> getByTitleAndClassification(String title, MPAClassification classification) {
        return movieDBRepository.findByTitleContainingIgnoreCaseAndClassificationContaining(title, classification.name().replace("_", "-")).stream().map(this::movieEntityToMovie).toList();
    }
}
