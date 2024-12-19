package dev.borega.api_movies.celeb.domain.exception;

public class CelebNotFoundException extends RuntimeException {
    public CelebNotFoundException() {
        super("Celebrity not found");
    }
}
