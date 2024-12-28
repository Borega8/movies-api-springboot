package dev.borega.api_movies.celeb.infrastructure.mapper;

import dev.borega.api_movies.celeb.domain.model.Celeb;
import dev.borega.api_movies.celeb.infrastructure.dto.BasicCelebDTO;
import dev.borega.api_movies.celeb.infrastructure.persistence.entity.CelebEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CelebMapper {
    CelebMapper INSTANCE = Mappers.getMapper(CelebMapper.class);

    BasicCelebDTO celebToBasicCelebDTO(Celeb celeb);
    Celeb basicCelebDTOToCeleb(BasicCelebDTO basicCelebDTO);

    @Mapping(source = "celebId", target = "id")
    @Mapping(source = "name", target = "fullName")
    @Mapping(source = "placeOfBirth", target = "birthPlace")
    Celeb celebEntityToCeleb(CelebEntity celebEntity);

    @Mapping(source = "id", target = "celebId")
    @Mapping(source = "fullName", target = "name")
    @Mapping(source = "birthPlace", target = "placeOfBirth")
    CelebEntity celebToCelebEntity(Celeb celeb);
}
