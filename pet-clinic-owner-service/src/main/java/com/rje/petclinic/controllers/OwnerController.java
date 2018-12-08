package com.rje.petclinic.controllers;

import com.rje.petclinic.model.Owner;
import com.rje.petclinic.model.Pet;
import com.rje.petclinic.repositories.OwnerRepository;
import com.rje.petclinic.repositories.PetRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/owners")
public class OwnerController {

    private PetRepository petRepository;
    private OwnerRepository ownerRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Owner createOwner(@RequestBody Owner owner) {
        Owner savedOwner = ownerRepository.save(owner);
        return savedOwner;
    }

    @GetMapping("/{ownerId}")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId) {
        return ownerRepository.findById(ownerId).get();
    }

    @GetMapping
    public List<Owner> findAllOwners() {
        List<Owner> owners = new ArrayList<>();
        ownerRepository.findAll().forEach(owners::add);
        return owners;
    }

    @GetMapping("/findByLastNameLike/{name}")
    public List<Owner> findAllByLastNameLike(@PathVariable("name") String name) {
        List<Owner> owners = new ArrayList<>();
        ownerRepository.findAllByLastNameLike(name).forEach(owners::add);
        return owners;
    }

    @GetMapping("/findByLastName/{name}")
    public Owner findOwnerByLastName(@PathVariable("name") String name) {
        return ownerRepository.findByLastName(name);
    }

    @PutMapping(value = "/{ownerId}")
    public Owner updateOwner(@PathVariable("ownerId") Long ownerId, @RequestBody Owner owner) {
        Owner savedOwner = ownerRepository.save(owner);
        return savedOwner;
    }

    @PostMapping("/{ownerId}/pets")
    public Pet createPet(@PathVariable("ownerId") Long ownerId, @RequestBody Pet pet) {
        Owner owner = ownerRepository.findById(ownerId).get();
        pet.setOwner(owner);
        Pet savedPet = petRepository.save(pet);
        return savedPet;
    }

    @PutMapping("/{ownerId}/pets/{petId}")
    public Pet savePet(@PathVariable("ownerId") Long ownerId, @PathVariable("petId") Long petId, @RequestBody Pet pet) {
        Owner owner = ownerRepository.findById(ownerId).get();
        pet.setOwner(owner);
        Pet savedPet = petRepository.save(pet);
        return savedPet;
    }

    @GetMapping("/findOwnerByPetId/{petId}")
    public Owner findOwnerByPetId(@PathVariable("petId") Long petId) {
        return ownerRepository.findOwnerByPetId(petId);
    }


}
