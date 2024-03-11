package com.example.favoritemoviesite.repository;

import com.example.favoritemoviesite.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, String> {

}
