package com.example.demo.services;

import com.example.demo.dto.ActorDto;
import com.example.demo.dto.MovieDto;
import com.example.demo.entities.Actors;
import com.example.demo.entities.Movie;
import com.example.demo.entities.MovieActors;
import com.example.demo.repositories.MovieActorsRepository;
import com.example.demo.repositories.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    private final MoviesRepository moviesRepository;
    private final ActorsService actorsService;

    private final MovieActorsRepository movieActorsRepository;

    @Autowired
    public MovieService(MoviesRepository moviesRepository, ActorsService actorsService, MovieActorsRepository movieActorsRepository) {
        this.moviesRepository = moviesRepository;
        this.actorsService = actorsService;
        this.movieActorsRepository = movieActorsRepository;
    }

    public List<Movie> getAll() {
        return this.moviesRepository.findAll();
    }

    public List<Movie> findByTitle(String title) {
        return this.moviesRepository.findAllByTitleLikeIgnoreCase("%" + title.toUpperCase() + "%");
    }

    public Movie findById(Long id) {
        return this.moviesRepository.findById(id).orElse(null);
    }

    @Transactional
    public void delete(Long id) {
        var movie = this.moviesRepository.findById(id).orElse(null);
        this.movieActorsRepository.deleteByMovie(movie);
        this.moviesRepository.deleteById(id);
    }

    @Transactional
    public Movie save(MovieDto movie) throws Exception {

        List<Actors> actorList = actorsService.saveMany(movie.getActors());

        Movie movieInsert = new Movie();
        movieInsert.setTitle(movie.getTitle());
        movieInsert.setDescription(movie.getDescription());
        movieInsert.setYear(movie.getYear());
        Movie movieInserted = this.moviesRepository.save(movieInsert);


        for (Actors actor : actorList) {
            MovieActors ma = new MovieActors();
            ma.setMovie(movieInserted);
            ma.setActor(actor);
            this.movieActorsRepository.save(ma);

        }
        return movieInserted;
    }


}
