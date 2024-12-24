package dev.borega.api_movies.celeb.infrastructure.mapper;

import dev.borega.api_movies.celeb.domain.model.Celeb;
import dev.borega.api_movies.celeb.infrastructure.dto.BasicCelebDTO;
import dev.borega.api_movies.celeb.infrastructure.persistence.entity.CelebEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CelebMapper {
    CelebMapper INSTANCE = Mappers.getMapper(CelebMapper.class);

    BasicCelebDTO celebToBasicCelebDTO(Celeb celeb);
    Celeb basicCelebDTOToCeleb(BasicCelebDTO basicCelebDTO);

    Celeb celebEntityToCeleb(CelebEntity celebEntity);
    CelebEntity celebToCelebEntity(Celeb celeb);
}
