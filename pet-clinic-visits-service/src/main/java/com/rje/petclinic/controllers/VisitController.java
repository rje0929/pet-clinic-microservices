package com.rje.petclinic.controllers;

import com.rje.petclinic.model.Visit;
import com.rje.petclinic.repositories.VisitRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class VisitController {

    private VisitRepository visitRepository;

    @PostMapping("owners/*/pets/{petId}/visits")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Visit visit,
                       @PathVariable("petId") Long petId) {
        visit.setPetId(petId);
        visitRepository.save(visit);
    }

    @GetMapping("owners/*/pets/{petId}/visits")
    public List<Visit> visits(@PathVariable("petId") Long petId) {
        List<Visit> visitsList = new ArrayList<>();
        visitRepository.findByPetId(petId).forEach(visitsList::add);
        return visitsList;
    }

    @GetMapping("pets/visits")
    public List<Visit> visitsByPetIds(@RequestParam("petId") List<Integer> petIds) {
        List<Visit> visitsList = new ArrayList<>();
        visitRepository.findByPetIdIn(petIds).forEach(visitsList::add);
        return visitsList;
    }

}
