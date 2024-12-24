package dev.borega.api_movies.celeb.infrastructure.controller;

import dev.borega.api_movies.celeb.aplication.CelebPort;
import dev.borega.api_movies.celeb.domain.exception.CelebNotFoundException;
import dev.borega.api_movies.celeb.domain.model.Celeb;
import dev.borega.api_movies.celeb.infrastructure.dto.BasicCelebDTO;
import dev.borega.api_movies.celeb.infrastructure.mapper.CelebMapper;
import dev.borega.api_movies.shared.domain.exception.InvalidValueException;
import dev.borega.api_movies.shared.domain.model.APIResponse;
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
    private final CelebMapper celebMapper = CelebMapper.INSTANCE;

    @GetMapping
    public ResponseEntity<APIResponse<List<BasicCelebDTO>>> getAllCelebs() {
        List<BasicCelebDTO> celebs = celebPort.getAll().stream().map(celebMapper::celebToBasicCelebDTO).toList();
        APIResponse<List<BasicCelebDTO>> res = new APIResponse<>(celebs);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<BasicCelebDTO>> getCeleb(@PathVariable Long id) {
        try {
            Celeb celeb = celebPort.getById(id);
            APIResponse<BasicCelebDTO> res = new APIResponse<>(celebMapper.celebToBasicCelebDTO(celeb));
            return ResponseEntity.ok(res);

        } catch (Exception e) {
            if (e instanceof CelebNotFoundException)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse<>(null, e.getMessage()));

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIResponse<>(null, e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> saveCeleb(@RequestBody BasicCelebDTO celebDTO) {
        try {
            Celeb celeb = celebPort.save(celebMapper.basicCelebDTOToCeleb(celebDTO));

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new APIResponse<>(celebMapper.celebToBasicCelebDTO(celeb)));
        } catch (Exception e) {
            if (e instanceof InvalidValueException)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIResponse<>(null, e.getMessage()));

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIResponse<>(null, e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCeleb(@PathVariable Long id, @RequestBody BasicCelebDTO celebDTO) {
        try {
            Celeb celeb = celebMapper.basicCelebDTOToCeleb(celebDTO);
            celeb.setId(id);

            Celeb celebUpdated = celebPort.update(celeb);
            return ResponseEntity.ok(new APIResponse<>(celebMapper.celebToBasicCelebDTO(celebUpdated)));

        } catch (Exception e) {
            if (e instanceof CelebNotFoundException)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse<>(null, e.getMessage()));

            if (e instanceof InvalidValueException)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIResponse<>(null, e.getMessage()));

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIResponse<>(null, "Unexpected Error"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCeleb(@PathVariable Long id) {
        try {
            celebPort.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        } catch (Exception e) {
            if (e instanceof CelebNotFoundException)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse<>(null, e.getMessage()));

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIResponse<>(null, "Unexpected Error"));
        }
    }

}
