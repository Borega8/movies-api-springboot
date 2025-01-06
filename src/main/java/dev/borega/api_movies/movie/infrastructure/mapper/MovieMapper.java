package dev.borega.api_movies.movie.infrastructure.mapper;

import dev.borega.api_movies.celeb.domain.model.Celeb;
import dev.borega.api_movies.celeb.infrastructure.dto.CelebNameDTO;
import dev.borega.api_movies.celeb.infrastructure.mapper.CelebMapper;
import dev.borega.api_movies.celeb.infrastructure.persistence.entity.CelebEntity;
import dev.borega.api_movies.movie.domain.model.Movie;
import dev.borega.api_movies.movie.infrastructure.dto.BasicMovieDTO;
import dev.borega.api_movies.movie.infrastructure.persistence.entity.MovieEntity;
import dev.borega.api_movies.movie.infrastructure.persistence.entity.MoviesHasCelebs;
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

    default MoviesHasCelebs celebToMoviesHasCelebs(Celeb celeb) {
        if ( celeb == null ) {
            return null;
        }

        CelebEntity celebEntity = CelebMapper.INSTANCE.celebToCelebEntity(celeb);
        MoviesHasCelebs moviesHasCelebs = new MoviesHasCelebs();

        moviesHasCelebs.setCeleb(celebEntity);

        return moviesHasCelebs;
    }

    default Celeb moviesHasCelebsToCeleb(MoviesHasCelebs moviesHasCelebs) {
        if ( moviesHasCelebs == null ) {
            return null;
        }

        Celeb celeb = new Celeb();

        celeb.setId(moviesHasCelebs.getCeleb().getCelebId());
        celeb.setFullName(moviesHasCelebs.getCeleb().getName());
        celeb.setBirthDate(moviesHasCelebs.getCeleb().getBirthDate());
        celeb.setBirthPlace(moviesHasCelebs.getCeleb().getPlaceOfBirth());
        celeb.setBiography(moviesHasCelebs.getCeleb().getBiography());
        celeb.setPhoto(moviesHasCelebs.getCeleb().getPhoto());

        return celeb;
    }

    @Mapping(source = "id", target = "id")
    @Mapping(source = "fullName", target = "name")
    CelebNameDTO celebToCelebNameDTO(Celeb celeb);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "fullName")
    Celeb celebNameDTOToCeleb(CelebNameDTO celebNameDTO);
}
