package dev.borega.api_movies.celeb.infrastructure.persistence.repository;

import dev.borega.api_movies.celeb.infrastructure.persistence.entity.CelebEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CelebDBRespository extends JpaRepository<CelebEntity, Long> {
}
