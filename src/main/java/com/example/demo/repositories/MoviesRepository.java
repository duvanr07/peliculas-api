package com.example.demo.repositories;

import com.example.demo.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MoviesRepository extends JpaRepository<Movie, Long> {
    List<Movie> findAllByTitleLikeIgnoreCase(String title);
}
