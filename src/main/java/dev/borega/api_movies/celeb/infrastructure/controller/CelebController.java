package dev.borega.api_movies.celeb.infrastructure.controller;

import dev.borega.api_movies.celeb.aplication.CelebPort;
import dev.borega.api_movies.celeb.domain.exception.CelebNotFoundException;
import dev.borega.api_movies.celeb.domain.model.Celeb;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/celebs")
@RequiredArgsConstructor
public class CelebController {

    private final CelebPort celebPort;

    @GetMapping
    public ResponseEntity<List<Celeb>> getAllCelebs() {
        List<Celeb> celebs = celebPort.getAll();
        return ResponseEntity.ok(celebs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Celeb> getCeleb(@PathVariable Long id) {
        try {
            Celeb celeb = celebPort.getById(id);
            return ResponseEntity.ok(celeb);

        } catch (Exception e) {
            if (e instanceof CelebNotFoundException)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Celeb> saveCeleb(@RequestBody Celeb celeb) {
        Celeb newCeleb = celebPort.save(celeb);

        return ResponseEntity.status(HttpStatus.CREATED).body(newCeleb);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCeleb(@PathVariable Long id, @RequestBody Celeb celeb) {
        try {
            Celeb celebUpdated = celebPort.update(celeb);
            return ResponseEntity.ok(celebUpdated);

        } catch (Exception e) {
            if (e instanceof CelebNotFoundException)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCeleb(@PathVariable Long id) {
        try {
            celebPort.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        } catch (Exception e) {
            if (e instanceof CelebNotFoundException)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
