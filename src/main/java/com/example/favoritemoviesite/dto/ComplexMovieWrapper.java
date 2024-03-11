package com.example.favoritemoviesite.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ComplexMovieWrapper {
    @JsonProperty("imdbID")
    private String id;
    @JsonProperty("Year")
    private int year;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Poster")
    private String posterURL;
    @JsonProperty("Rated")
    private String rating;
    @JsonProperty("Released")
    private String released;
    @JsonProperty("Runtime")
    private String runtime;
    @JsonProperty("Genre")
    private String genre;
    @JsonProperty("Director")
    private String director;
    @JsonProperty("Writer")
    private String writers;
    @JsonProperty("Actors")
    private String actors;
    @JsonProperty("Plot")
    private String plot;
    @JsonProperty("Language")
    private String language;
    @JsonProperty("Country")
    private String country;
    @JsonProperty("Awards")
    private String awards;
    @JsonProperty("Ratings")
    private RatingsWrapper[] ratings;
}