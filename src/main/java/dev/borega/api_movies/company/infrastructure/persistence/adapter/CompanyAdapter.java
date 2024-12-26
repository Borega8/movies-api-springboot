package dev.borega.api_movies.company.infrastructure.persistence.adapter;

import dev.borega.api_movies.company.domain.model.Company;
import dev.borega.api_movies.company.domain.repository.CompanyRepository;
import dev.borega.api_movies.company.infrastructure.mapper.CompanyMapper;
import dev.borega.api_movies.company.infrastructure.persistence.entity.CompanyEntity;
import dev.borega.api_movies.company.infrastructure.persistence.respository.CompanyDBRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CompanyAdapter implements CompanyRepository {

    private final CompanyDBRepository companyDBRepository;
    private final CompanyMapper companyMapper = CompanyMapper.INSTANCE;

    @Override
    public List<Company> getAll() {
        return companyDBRepository.findAll().stream().map(companyMapper::companyEntityToCompany).toList();
    }

    @Override
    public Optional<Company> getById(Long id) {
        return companyDBRepository.findById(id).map(companyMapper::companyEntityToCompany);
    }

    @Override
    public Company save(Company company) {
        CompanyEntity companyEntity = companyDBRepository.save(companyMapper.companyToCompanyEntity(company));
        return companyMapper.companyEntityToCompany(companyEntity);
    }

    @Override
    public Company update(Company company) {
        CompanyEntity companyEntity = companyDBRepository.save(companyMapper.companyToCompanyEntity(company));
        return companyMapper.companyEntityToCompany(companyEntity);
    }

    @Override
    public void delete(Long id) {
        companyDBRepository.deleteById(id);
    }
}
