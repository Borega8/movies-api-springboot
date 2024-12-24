package dev.borega.api_movies.shared.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class APIResponse<T> {
    private final T data;
    private final String error;

    public APIResponse(T data) {
        this.data = data;
        this.error = null;
    }
}
