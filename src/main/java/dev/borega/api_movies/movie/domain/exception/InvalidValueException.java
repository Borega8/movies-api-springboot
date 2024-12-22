package dev.borega.api_movies.movie.domain.exception;

public class InvalidValueException extends RuntimeException {
    public InvalidValueException(String message) {
        super(message);
    }
}
