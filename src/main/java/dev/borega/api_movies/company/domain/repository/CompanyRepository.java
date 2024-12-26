package dev.borega.api_movies.company.domain.repository;

import dev.borega.api_movies.company.domain.model.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository {
    List<Company> getAll();
    Optional<Company> getById(Long id);
    Company save(Company company);
    Company update(Company company);
    void delete(Long id);
}
