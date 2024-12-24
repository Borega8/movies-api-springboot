package dev.borega.api_movies.celeb.infrastructure.persistence.adapter;

import dev.borega.api_movies.celeb.domain.model.Celeb;
import dev.borega.api_movies.celeb.domain.repository.CelebRepository;
import dev.borega.api_movies.celeb.infrastructure.persistence.entity.CelebEntity;
import dev.borega.api_movies.celeb.infrastructure.persistence.repository.CelebDBRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CelebAdapter implements CelebRepository {

    private final CelebDBRespository celebDBRespository;

    private Celeb celebEntityToCeleb(CelebEntity celebEntity) {
        return new Celeb(celebEntity.getCelebId(), celebEntity.getName(), celebEntity.getBirthDate(), celebEntity.getPlaceOfBirth(), celebEntity.getBiography(), celebEntity.getPhoto());
    }

    private CelebEntity celebToCelebEntity(Celeb celeb) {
        return new CelebEntity(celeb.getId(), celeb.getFullName(), celeb.getBirthDate(), celeb.getBirthPlace(), celeb.getBiography(), celeb.getPhoto());
    }

    @Override
    public List<Celeb> getAll() {
        return celebDBRespository.findAll(Sort.by("celebId")).stream().map(this::celebEntityToCeleb).toList();
    }

    @Override
    public Optional<Celeb> getById(Long id) {
        return celebDBRespository.findById(id).map(this::celebEntityToCeleb);
    }

    @Override
    public Celeb save(Celeb celeb) {
        CelebEntity celebEntity = celebDBRespository.save(celebToCelebEntity(celeb));
        return celebEntityToCeleb(celebEntity);
    }

    @Override
    public Celeb update(Celeb celeb) {
        CelebEntity celebEntity = celebDBRespository.save(celebToCelebEntity(celeb));
        return celebEntityToCeleb(celebEntity);
    }

    @Override
    public void delete(Long id) {
        celebDBRespository.deleteById(id);
    }
}
