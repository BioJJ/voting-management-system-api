package com.biojj.app.controller;

import com.biojj.app.domain.Collaborator;
import com.biojj.app.domain.dtos.CollaboratorDTO;
import com.biojj.app.service.CollaboratorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/collaborator")
public class CollaboratorController {

    private final CollaboratorService service;

    public CollaboratorController(CollaboratorService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CollaboratorDTO> findById(@PathVariable Integer id) {
        Collaborator obj = this.service.findById(id);
        return ResponseEntity.ok().body(new CollaboratorDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<CollaboratorDTO>> findAll() {
        List<Collaborator> list = service.findAll();
        List<CollaboratorDTO> listDTO = list.stream().map(CollaboratorDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<CollaboratorDTO> create(@Valid @RequestBody CollaboratorDTO objDTO) {
        Collaborator obj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CollaboratorDTO> update(@PathVariable Integer id, @Valid @RequestBody CollaboratorDTO objDTO) {
        Collaborator obj = service.update(id, objDTO);
        return ResponseEntity.ok().body(new CollaboratorDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CollaboratorDTO> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/register")
    public ResponseEntity<CollaboratorDTO> register(@Valid @RequestBody CollaboratorDTO objDTO) {
        Collaborator obj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
