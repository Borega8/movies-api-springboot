package dev.borega.api_movies.company.infrastructure.persistence.respository;

import dev.borega.api_movies.company.infrastructure.persistence.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyDBRepository extends JpaRepository<CompanyEntity, Long> {
}
