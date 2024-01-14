package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "actors")
@Setter
@Getter
@NoArgsConstructor
public class Actors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "characterr_name")
    private String characterName;

    @Column(nullable = false, name = "original_name")
    private String originalName;

    @JsonIgnore
    @OneToMany(mappedBy = "actor")
    private List<MovieActors> movieActors;
}
