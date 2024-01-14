package com.example.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class MovieDto {
    private String title;
    private String description;
    private Long year;
    private List<ActorDto> actors;
}
