package com.example.favoritemoviesite.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingsWrapper {
    @JsonProperty("Source")
    private String source;
    @JsonProperty("Value")
    private String value;
}