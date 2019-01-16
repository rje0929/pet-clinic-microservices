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
        return ownerRepository.save(owner);
    }

    @GetMapping("/{ownerId}")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId) {
        return ownerRepository.findById(ownerId).orElseThrow(() -> new RuntimeException("owner with id=" + ownerId + " not found"));
    }

    @GetMapping
    public List<Owner> findAllOwners() {
        List<Owner> owners = new ArrayList<>();
        ownerRepository.findAll().forEach(owners::add);
        return owners;
    }

    @GetMapping("/findByLastNameLike/{name}")
    public List<Owner> findAllByLastNameLike(@PathVariable("name") String name) {
        return new ArrayList<>(ownerRepository.findAllByLastNameLike(name));
    }

    @GetMapping("/findByLastName/{name}")
    public Owner findOwnerByLastName(@PathVariable("name") String name) {
        return ownerRepository.findByLastName(name);
    }

    @PutMapping(value = "/{ownerId}")
    public Owner updateOwner(@PathVariable("ownerId") Long ownerId, @RequestBody Owner owner) {
        return ownerRepository.save(owner);
    }

    @PostMapping("/{ownerId}/pets")
    public Pet createPet(@PathVariable("ownerId") Long ownerId, @RequestBody Pet pet) {
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(() -> new RuntimeException("owner with id=" + ownerId + " not found"));
        pet.setOwner(owner);
        return petRepository.save(pet);
    }

    @PutMapping("/{ownerId}/pets/{petId}")
    public Pet savePet(@PathVariable("ownerId") Long ownerId, @PathVariable("petId") Long petId, @RequestBody Pet pet) {
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(() -> new RuntimeException("owner with id=" + ownerId + " not found"));
        pet.setOwner(owner);
        return petRepository.save(pet);
    }

    @GetMapping("/findOwnerByPetId/{petId}")
    public Owner findOwnerByPetId(@PathVariable("petId") Long petId) {
        return ownerRepository.findOwnerByPetId(petId);
    }


}
