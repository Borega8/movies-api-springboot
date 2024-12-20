package dev.borega.api_movies.movie.domain.model;

public enum MPAClassification {
    G("G", "General Audiences"),
    PG("PG", "Parental Guidance Suggested"),
    PG_13("PG-13", "Parents Strongly Cautioned"),
    R("R", "Restricted"),
    NC_17("NC-17", "Adults Only");

    MPAClassification(String classification, String description) {}
}
