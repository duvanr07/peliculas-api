package com.example.demo.repositories;

import com.example.demo.entities.Movie;
import com.example.demo.entities.MovieActors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieActorsRepository extends JpaRepository<MovieActors, Long> {
    void deleteByMovie(Movie movie);

}
