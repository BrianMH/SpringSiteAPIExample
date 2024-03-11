package com.example.favoritemoviesite.dto;

import com.example.favoritemoviesite.model.Movie;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Tries to wrangle the output of the JSON returned by search method.
 */
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchWrapper {
    @JsonProperty("Search")
    List<Movie> wrappedMovies;

    public List<Movie> getWrappedMovies() {
        if(wrappedMovies == null) {
            return new ArrayList<Movie>();
        }

        return this.wrappedMovies;
    }

    @Override
    public String toString() {
        return wrappedMovies.toString();
    }
}
