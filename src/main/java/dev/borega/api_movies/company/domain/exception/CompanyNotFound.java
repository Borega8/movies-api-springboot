package dev.borega.api_movies.company.domain.exception;

public class CompanyNotFound extends RuntimeException {
    public CompanyNotFound(String message) {
        super("Company not found");
    }
}
