package dev.borega.api_movies.celeb.infrastructure.mapper;

import dev.borega.api_movies.celeb.domain.model.Celeb;
import dev.borega.api_movies.celeb.infrastructure.dto.BasicCelebDTO;
import dev.borega.api_movies.celeb.infrastructure.persistence.entity.CelebEntity;
import dev.borega.api_movies.movie.domain.model.Movie;
import dev.borega.api_movies.movie.infrastructure.mapper.MovieMapper;
import dev.borega.api_movies.movie.infrastructure.persistence.entity.MoviesHasCelebs;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

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

    default Movie moviesHasCelebsToMovie(MoviesHasCelebs moviesHasCelebs) {
        if ( moviesHasCelebs == null ) {
            return null;
        }

        Long id = moviesHasCelebs.getMovie().getId();

        String title = moviesHasCelebs.getMovie().getTitle();
        LocalDate releaseDate = moviesHasCelebs.getMovie().getReleaseDate();
        String poster = moviesHasCelebs.getMovie().getPoster();
        Double rating = moviesHasCelebs.getMovie().getRating();

        return new Movie(id, title, null, null, releaseDate, poster, null, rating, null, null, null);
    }

    default MoviesHasCelebs movieToMoviesHasCelebs(Movie movie) {
        if ( movie == null ) {
            return null;
        }

        MoviesHasCelebs moviesHasCelebs = new MoviesHasCelebs();

        moviesHasCelebs.setMovie(MovieMapper.INSTANCE.movieToMovieEntity(movie));

        return moviesHasCelebs;
    }
}
