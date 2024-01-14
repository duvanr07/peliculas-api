package com.example.demo.controller;


import com.example.demo.dto.MovieDto;
import com.example.demo.entities.Movie;
import com.example.demo.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movies")
@CrossOrigin(origins = "*")
public class MovieController {

    private final MovieService movieService;


    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public Movie status(@RequestBody MovieDto movie) throws Exception {
        return this.movieService.save(movie);
    }

    @DeleteMapping("/{idMovie}")
    public void delete(@PathVariable Long idMovie) {
        this.movieService.delete(idMovie);
    }

    @GetMapping("/{idMovie}")
    public ResponseEntity<Movie> findOne(@PathVariable Long idMovie) {
        var movie = this.movieService.findById(idMovie);
        if (movie == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movie);

    }

    @GetMapping("/search")
    public List<Movie> search(@RequestParam String term) {
        return this.movieService.findByTitle(term);
    }

    @GetMapping()
    public List<Movie> getAllMovies() {
        return this.movieService.getAll();
    }


}
