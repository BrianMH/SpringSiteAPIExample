package com.example.favoritemoviesite.service;

import com.example.favoritemoviesite.dto.ComplexMovieWrapper;
import com.example.favoritemoviesite.model.Movie;
import com.example.favoritemoviesite.model.Rating;
import com.example.favoritemoviesite.repository.MovieRepository;
import com.example.favoritemoviesite.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepo;

    @Autowired
    RatingRepository ratingRepo;

    public List<Movie> getSavedMovies() {
        return movieRepo.findAll();
    }

    /**
     * Converts a passed complexly wrapped movie into a move and adds it into our repository
     * @return
     */
    public Movie addMovie(ComplexMovieWrapper toAdd) {
        // first populate our movie
        Movie converted = new Movie();

        // set available elements
        converted.setId(toAdd.getId());
        converted.setYear(toAdd.getYear());
        converted.setTitle(toAdd.getTitle());
        converted.setGenre(toAdd.getGenre());
        converted.setRating(toAdd.getRating());
        converted.setReleased(toAdd.getReleased());
        converted.setRatings(Arrays.stream(toAdd.getRatings()).map(rWrapper -> {
            // move wrapper to entity object
            Rating replacement = new Rating();
            replacement.setContent(rWrapper.getValue());
            replacement.setSourceSite(rWrapper.getSource());

            // save element
            ratingRepo.save(replacement);

            return replacement;
        }).collect(Collectors.toSet()));

        // now we can convert the blob if necessary
        converted.setPosterURL(toAdd.getPosterURL());
        try {
            converted.convertURLToByteCode();
        } catch(Exception e) {
            System.out.println("Failed to convert file... BLOB will remain empty.");
        }

        return movieRepo.save(converted);
    }

    /**
     * Deletes a movie entry based on the movie's unique ID
     * @param movieId
     */
    public void removeById(String movieId) {
        movieRepo.deleteById(movieId);
    }
}
