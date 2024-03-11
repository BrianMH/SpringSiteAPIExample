package com.example.favoritemoviesite.controller;

import com.example.favoritemoviesite.dto.ComplexMovieWrapper;
import com.example.favoritemoviesite.model.Movie;
import com.example.favoritemoviesite.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Manages all requests to the movie API
 */
@Controller
public class MovieController {
    @Autowired
    MovieService mService;

    @GetMapping("/showList")
    public String getSavedMovies(Model model) {
        model.addAttribute("allMovies", mService.getSavedMovies());

        return "myList";
    }

    @PostMapping("/addMovie")
    @ResponseBody
    public ResponseEntity<?> addMovieToList(@RequestBody ComplexMovieWrapper toAdd) {

        return new ResponseEntity<Movie>(mService.addMovie(toAdd), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteMovie")
    public ResponseEntity<?> removeMovieFromList(@RequestParam String movieId) {
        mService.removeById(movieId);

        return ResponseEntity.noContent().build();
    }
}
