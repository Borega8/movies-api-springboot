package dev.borega.api_movies.movie.infrastructure.controller;

import dev.borega.api_movies.celeb.domain.exception.CelebNotFoundException;
import dev.borega.api_movies.movie.application.MoviePort;
import dev.borega.api_movies.movie.infrastructure.dto.BasicMovieDTO;
import dev.borega.api_movies.movie.infrastructure.mapper.MovieMapper;
import dev.borega.api_movies.shared.domain.exception.InvalidValueException;
import dev.borega.api_movies.movie.domain.exception.MovieNotFoundException;
import dev.borega.api_movies.movie.domain.model.MPAClassification;
import dev.borega.api_movies.movie.domain.model.Movie;
import dev.borega.api_movies.shared.domain.model.APIResponse;
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
    private final MovieMapper movieMapper = MovieMapper.INSTANCE;

    @GetMapping
    public ResponseEntity<APIResponse<List<BasicMovieDTO>>> getAllMovies(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) MPAClassification classification
    ) {
        List<Movie> movies;

        if (classification != null && title != null) {
            movies = moviePort.getByTitleAndClassification(title, classification);
            return ResponseEntity.ok(new APIResponse<>(movies.stream().map(movieMapper::movieToBasicMovieDTO).toList()));
        }

        if (classification != null) {
            movies = moviePort.getByClassification(classification);
            return ResponseEntity.ok(new APIResponse<>(movies.stream().map(movieMapper::movieToBasicMovieDTO).toList()));
        }

        if (title != null) {
            movies = moviePort.getByTitle(title);
            return ResponseEntity.ok(new APIResponse<>(movies.stream().map(movieMapper::movieToBasicMovieDTO).toList()));
        }

        return ResponseEntity.ok(new APIResponse<>(moviePort.getAll().stream().map(movieMapper::movieToBasicMovieDTO).toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<BasicMovieDTO>> getMovie(@PathVariable Long id) {
        try {
            Movie movie = moviePort.getById(id);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new APIResponse<>(movieMapper.movieToBasicMovieDTO(movie)));

        } catch (Exception e) {
            if (e instanceof MovieNotFoundException)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse<>(null, e.getMessage()));

            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> saveMovie(@RequestBody BasicMovieDTO movieDTO) {
        try {
            Movie movie = moviePort.save(movieMapper.basicMovieDTOToMovie(movieDTO));

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new APIResponse<>(movieMapper.movieToBasicMovieDTO(movie)));

        } catch (Exception e) {
            if (e instanceof InvalidValueException)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIResponse<>(null, e.getMessage()));

            if (e instanceof IllegalArgumentException)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIResponse<>(null, e.getMessage()));


            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable Long id, @RequestBody BasicMovieDTO movieDTO) {
        try {
            Movie movie = movieMapper.basicMovieDTOToMovie(movieDTO);
            movie.setId(id);

            Movie movieUpdated = moviePort.update(movie);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new APIResponse<>(movieMapper.movieToBasicMovieDTO(movieUpdated)));

        } catch (Exception e) {
            if (e instanceof MovieNotFoundException)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse<>(null, e.getMessage()));

            if (e instanceof InvalidValueException)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIResponse<>(null, e.getMessage()));

            if (e instanceof IllegalArgumentException)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIResponse<>(null, e.getMessage()));

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
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse<>(null, e.getMessage()));

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
