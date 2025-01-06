package dev.borega.api_movies.celeb.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CelebRole {
    DIRECTOR("Director"),
    WRITER("Writer"),
    ACTOR("Actor");

    private final String value;
}
