package dev.borega.api_movies.company.application;

import dev.borega.api_movies.company.domain.model.Company;

import java.util.List;

public interface CompanyPort {
    List<Company> getAll();
    Company getById(Long id);
    Company save(Company company);
    Company update(Company company);
    void delete(Long id);
}
