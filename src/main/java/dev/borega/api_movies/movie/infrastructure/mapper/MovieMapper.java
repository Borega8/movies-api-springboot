package dev.borega.api_movies.movie.infrastructure.mapper;

import dev.borega.api_movies.movie.domain.model.Movie;
import dev.borega.api_movies.movie.infrastructure.dto.BasicMovieDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MovieMapper {
    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    BasicMovieDTO movieToBasicMovieDTO(Movie movie);
    Movie basicMovieDTOToMovie(BasicMovieDTO basicMovieDTO);
}
