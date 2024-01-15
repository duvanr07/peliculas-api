package com.example.demo.repositories;

import com.example.demo.entities.Actors;
import com.example.demo.entities.Movie;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ActorsRepository extends ListCrudRepository<Actors, Long> {
    List<Actors> findAllByOriginalNameLikeIgnoreCase(String title);
}
