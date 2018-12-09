package com.rje.petclinic.controllers;

import com.rje.petclinic.model.Pet;
import com.rje.petclinic.model.PetType;
import com.rje.petclinic.repositories.PetRepository;
import com.rje.petclinic.repositories.PetTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/pets")
public class PetController {

    public PetRepository petRepository;
    public PetTypeRepository petTypeRepository;

    @GetMapping("/petTypes")
    public List<PetType> findPetTypes() {
        List<PetType> petTypes = new ArrayList<>();
        petTypeRepository.findAll().forEach(petTypes::add);
        return petTypes;
    }

    @GetMapping("/{petId}")
    public Pet findPetById(@PathVariable("petId") Long petId) {
        return petRepository.findById(petId).get();
    }

}
