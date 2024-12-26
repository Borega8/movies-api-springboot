package dev.borega.api_movies.company.application;

import dev.borega.api_movies.company.domain.exception.CompanyNotFoundException;
import dev.borega.api_movies.company.domain.model.Company;
import dev.borega.api_movies.company.domain.repository.CompanyRepository;
import dev.borega.api_movies.shared.domain.exception.InvalidValueException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CompanyService implements CompanyPort {

    private final CompanyRepository companyRepository;

    @Override
    public List<Company> getAll() {
        return companyRepository.getAll();
    }

    @Override
    public Company getById(Long id) {
        return companyRepository.getById(id).orElseThrow(CompanyNotFoundException::new);
    }

    @Override
    public Company save(Company company) {
        validateCompany(company);
        return companyRepository.save(company);
    }

    @Override
    public Company update(Company company) {
        validateCompany(company);
        companyRepository.getById(company.getId()).orElseThrow(CompanyNotFoundException::new);
        return companyRepository.update(company);
    }

    @Override
    public void delete(Long id) {
        companyRepository.getById(id).orElseThrow(CompanyNotFoundException::new);
        companyRepository.delete(id);
    }

    private void validateCompany(Company company) {
        if (!company.isValidFoundedDate())
            throw new InvalidValueException("Founded date cannot be after today");
    }
}
