package dev.borega.api_movies.movie.infrastructure.controller;

import dev.borega.api_movies.celeb.domain.exception.CelebNotFoundException;
import dev.borega.api_movies.movie.application.MoviePort;
import dev.borega.api_movies.shared.domain.exception.InvalidValueException;
import dev.borega.api_movies.movie.domain.exception.MovieNotFoundException;
import dev.borega.api_movies.movie.domain.model.MPAClassification;
import dev.borega.api_movies.movie.domain.model.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MoviePort moviePort;

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) MPAClassification classification
    ) {
        if (classification != null && title != null)
            return ResponseEntity.ok(moviePort.getByTitleAndClassification(title, classification));

        if (classification != null)
            return ResponseEntity.ok(moviePort.getByClassification(classification));

        if (title != null)
            return ResponseEntity.ok(moviePort.getByTitle(title));

        return ResponseEntity.ok(moviePort.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(moviePort.getById(id));

        } catch (Exception e) {
            if (e instanceof MovieNotFoundException)
                return ResponseEntity.notFound().build();

            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> saveMovie(@RequestBody Movie movie) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(moviePort.save(movie));

        } catch (Exception e) {
            if (e instanceof InvalidValueException)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

            if (e instanceof IllegalArgumentException)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());


            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        try {
            return ResponseEntity.ok(moviePort.update(movie));

        } catch (Exception e) {
            if (e instanceof MovieNotFoundException)
                return ResponseEntity.notFound().build();

            if (e instanceof InvalidValueException)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

            if (e instanceof IllegalArgumentException)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        try {
            moviePort.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        } catch (Exception e) {
            if (e instanceof CelebNotFoundException)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
