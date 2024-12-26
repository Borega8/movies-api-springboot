package dev.borega.api_movies.company.infrastructure.controller;

import dev.borega.api_movies.company.application.CompanyPort;
import dev.borega.api_movies.company.infrastructure.dto.BasicCompanyDTO;
import dev.borega.api_movies.company.infrastructure.mapper.CompanyMapper;
import dev.borega.api_movies.shared.domain.model.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyPort companyPort;
    private final CompanyMapper companyMapper = CompanyMapper.INSTANCE;

    @GetMapping
    public ResponseEntity<APIResponse<List<BasicCompanyDTO>>> getAllCompanies() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new APIResponse<>(
                        companyPort.getAll()
                                .stream()
                                .map(companyMapper::companyToBasicCompanyDTO)
                                .toList()
                ));
    }
}
