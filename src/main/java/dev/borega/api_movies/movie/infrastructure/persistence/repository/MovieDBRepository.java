package dev.borega.api_movies.movie.infrastructure.persistence.repository;

import dev.borega.api_movies.movie.infrastructure.persistence.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieDBRepository extends JpaRepository<MovieEntity, Long> {
    List<MovieEntity> findByTitleContainingIgnoreCase(String title);
    List<MovieEntity> findByClassificationIsIgnoreCase(String classification);
    List<MovieEntity> findByTitleContainingIgnoreCaseAndClassificationContaining(String title, String classification);
}
