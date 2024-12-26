package dev.borega.api_movies.company.infrastructure.mapper;

import dev.borega.api_movies.company.domain.model.Company;
import dev.borega.api_movies.company.infrastructure.dto.BasicCompanyDTO;
import dev.borega.api_movies.company.infrastructure.persistence.entity.CompanyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    Company companyEntityToCompany(CompanyEntity companyEntity);
    CompanyEntity companyToCompanyEntity(Company company);

    Company basicCompanyDTOToCompany(BasicCompanyDTO basicCompanyDTO);
    BasicCompanyDTO companyToBasicCompanyDTO(Company company);
}
