package com.example.demo.repositories;

import com.example.demo.entities.Actors;
import org.springframework.data.repository.ListCrudRepository;

public interface ActorsRepository extends ListCrudRepository<Actors, Long> {
}
