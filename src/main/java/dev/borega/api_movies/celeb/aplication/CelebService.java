package dev.borega.api_movies.celeb.aplication;

import dev.borega.api_movies.celeb.domain.exception.CelebNotFoundException;
import dev.borega.api_movies.celeb.domain.model.Celeb;
import dev.borega.api_movies.celeb.domain.repository.CelebRepository;
import dev.borega.api_movies.shared.domain.exception.InvalidValueException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CelebService implements CelebPort {

    private final CelebRepository celebRepository;

    @Override
    public List<Celeb> getAll() {
        return celebRepository.getAll();
    }

    @Override
    public Celeb getById(Long id) {
        return celebRepository.getById(id).orElseThrow(CelebNotFoundException::new);
    }

    @Override
    public Celeb save(Celeb celeb) {
        validateCeleb(celeb);
        return celebRepository.save(celeb);
    }

    @Override
    public Celeb update(Celeb celeb) {
        validateCeleb(celeb);

        Optional<Celeb> celebOptional = celebRepository.getById(celeb.getId());

        if (celebOptional.isEmpty())
            throw new CelebNotFoundException();

        return celebRepository.update(celeb);
    }

    @Override
    public void delete(Long id) {
        Optional<Celeb> celebOptional = celebRepository.getById(id);

        if (celebOptional.isEmpty())
            throw new CelebNotFoundException();

        celebRepository.delete(id);
    }

    private void validateCeleb(Celeb celeb) {
        if (!celeb.isValidBirthdate())
            throw new InvalidValueException("Birthdate cannot be after today");
    }
}

