package dev.borega.api_movies.movie.infrastructure.mapper;

import dev.borega.api_movies.movie.domain.model.Movie;
import dev.borega.api_movies.movie.infrastructure.dto.BasicMovieDTO;
import dev.borega.api_movies.movie.infrastructure.persistence.entity.MovieEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MovieMapper {
    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    BasicMovieDTO movieToBasicMovieDTO(Movie movie);
    Movie basicMovieDTOToMovie(BasicMovieDTO basicMovieDTO);

    MovieEntity movieToMovieEntity(Movie movie);
    Movie movieEntityToMovie(MovieEntity movieEntity);
}
