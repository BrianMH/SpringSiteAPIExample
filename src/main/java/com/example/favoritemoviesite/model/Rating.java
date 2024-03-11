package com.example.favoritemoviesite.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table
public class Rating {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String sourceSite;
    private String content;
}
