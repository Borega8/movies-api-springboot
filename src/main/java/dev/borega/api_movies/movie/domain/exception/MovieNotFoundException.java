package dev.borega.api_movies.movie.domain.exception;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException() {
        super("Movie not found");
    }
}
