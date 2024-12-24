package dev.borega.api_movies.celeb.infrastructure.persistence.adapter;

import dev.borega.api_movies.celeb.domain.model.Celeb;
import dev.borega.api_movies.celeb.domain.repository.CelebRepository;
import dev.borega.api_movies.celeb.infrastructure.mapper.CelebMapper;
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
    private final CelebMapper celebMapper = CelebMapper.INSTANCE;

    @Override
    public List<Celeb> getAll() {
        return celebDBRespository.findAll(Sort.by("celebId")).stream().map(celebMapper::celebEntityToCeleb).toList();
    }

    @Override
    public Optional<Celeb> getById(Long id) {
        return celebDBRespository.findById(id).map(celebMapper::celebEntityToCeleb);
    }

    @Override
    public Celeb save(Celeb celeb) {
        CelebEntity celebEntity = celebDBRespository.save(celebMapper.celebToCelebEntity(celeb));
        return celebMapper.celebEntityToCeleb(celebEntity);
    }

    @Override
    public Celeb update(Celeb celeb) {
        CelebEntity celebEntity = celebDBRespository.save(celebMapper.celebToCelebEntity(celeb));
        return celebMapper.celebEntityToCeleb(celebEntity);
    }

    @Override
    public void delete(Long id) {
        celebDBRespository.deleteById(id);
    }
}
