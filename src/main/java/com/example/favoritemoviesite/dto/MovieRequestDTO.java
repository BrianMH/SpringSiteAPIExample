package com.example.favoritemoviesite.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents the object for which a user makes a request for a movie
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovieRequestDTO {
    private String title = null;
    private Integer year = null;

    @Override
    public String toString() {
        return "{Movie- Name: " + this.title + ", Year: " + this.year + "}";
    }
}
