package com.example.demo.services;

import com.example.demo.dto.ActorDto;
import com.example.demo.entities.Actors;
import com.example.demo.repositories.ActorsRepository;
import com.example.demo.repositories.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ActorsService {

    private final ActorsRepository actorsRepository;

    @Autowired
    public ActorsService(ActorsRepository actorsRepository) {
        this.actorsRepository = actorsRepository;
    }

    public List<Actors> getAll() {
        return this.actorsRepository.findAll();
    }

    public Actors update(ActorDto actorDto) {

        Actors actor = new Actors();
        actor.setId(actorDto.getId());
        actor.setCharacterName(actorDto.getCharacterName());
        actor.setOriginalName(actorDto.getCharacterName());
        return this.actorsRepository.save(actor);
    }

    public Actors save(ActorDto actorDto) {
        Actors actor = new Actors();
        actor.setCharacterName(actorDto.getCharacterName());
        actor.setOriginalName(actorDto.getCharacterName());
        return this.actorsRepository.save(actor);
    }


    public List<Actors> saveMany(List<ActorDto> actors) {

        List<Actors> actorList = new ArrayList<Actors>();

        for (ActorDto actorDto : actors) {
            Actors actor = new Actors();
            actor.setId(actorDto.getId());
            if (actor.getId() == null) {
                actor.setCharacterName(actorDto.getCharacterName());
                actor.setOriginalName(actorDto.getCharacterName());
            }

            actorList.add(actor);
        }
        return this.actorsRepository.saveAll(actorList);

    }

    public Actors findById(Long id) {
        return this.actorsRepository.findById(id).orElse(null);
    }

    public boolean existsById(Long id) {
        return this.actorsRepository.existsById(id);
    }

    public void deleteById(Long id) {
        this.actorsRepository.deleteById(id);
    }
}
