package com.example.favoritemoviesite.controller;

import com.example.favoritemoviesite.dto.ComplexMovieWrapper;
import com.example.favoritemoviesite.dto.MovieRequestDTO;
import com.example.favoritemoviesite.dto.SearchWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Controller
public class RequestController {
    @Autowired
    WebClient movieReqClient;

    private static final String DEFAULT_API_KEY = "b79fdda2";

    @GetMapping("/searchMovies")
    public String getMoviesFromAPI(Model model, MovieRequestDTO movieReq) {
        // Get our response
        SearchWrapper res = movieReqClient.get().uri(uriBuilder -> uriBuilder.queryParam("apikey", DEFAULT_API_KEY)
                                                         .queryParam("s", movieReq.getTitle())
                                                         .queryParam("y", movieReq.getYear())
                                                         .build())
                                    .retrieve()
                                    .bodyToMono(SearchWrapper.class)
                                    .block();

        // deserialize into object
        model.addAttribute("movieList", res.getWrappedMovies());
        model.addAttribute("movieTitle", movieReq.getTitle());

        return "search";
    }

    @PostMapping(value = "/getById", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ComplexMovieWrapper acquireMovieFromID(@RequestBody Map<String, String> userInput) {
        return movieReqClient.get().uri(uriBuilder -> uriBuilder.queryParam("apikey", DEFAULT_API_KEY)
                        .queryParam("i", userInput.get("mID"))
                        .build())
                .retrieve()
                .bodyToMono(ComplexMovieWrapper.class)
                .block();
    }
}
