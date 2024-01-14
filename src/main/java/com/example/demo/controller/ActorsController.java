package com.example.demo.controller;

import com.example.demo.dto.ActorDto;
import com.example.demo.entities.Actors;
import com.example.demo.services.ActorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/actors")
public class ActorsController {

    private final ActorsService actorsService;

    @Autowired
    public ActorsController(ActorsService actorsService) {
        this.actorsService = actorsService;
    }


    @GetMapping
    public ResponseEntity<List<Actors>> getAll() {
        return ResponseEntity.ok(this.actorsService.getAll());
    }

    @GetMapping("/{idActor}")
    public ResponseEntity<Actors> get(@PathVariable long idActor) {
        return ResponseEntity.ok(this.actorsService.findById(idActor));
    }

    @PostMapping
    public ResponseEntity<Actors> add(@RequestBody ActorDto actorDto) {
        if (actorDto.getId() != null) {
            actorDto.setId(null);
        }
        return ResponseEntity.ok(this.actorsService.save(actorDto));
    }

    @PutMapping
    public ResponseEntity<Actors> update(@RequestBody ActorDto actorDto) {

        if (actorDto.getId() != null && this.actorsService.existsById(actorDto.getId())) {
            return ResponseEntity.ok(this.actorsService.update(actorDto));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idActor}")
    public  ResponseEntity<Void> delete (@PathVariable Long idActor){

        if ( this.actorsService.existsById(idActor)) {
            this.actorsService.deleteById(idActor);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }


}
