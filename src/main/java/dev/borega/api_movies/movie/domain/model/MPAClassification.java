package dev.borega.api_movies.movie.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MPAClassification {
    G("G", "General Audiences"),
    PG("PG", "Parental Guidance Suggested"),
    PG_13("PG-13", "Parents Strongly Cautioned"),
    R("R", "Restricted"),
    NC_17("NC-17", "Adults Only");

    private final String classification;
    private final String description;
}
