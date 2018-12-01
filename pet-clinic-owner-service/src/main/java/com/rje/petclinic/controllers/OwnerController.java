package com.rje.petclinic.controllers;

import com.rje.petclinic.model.Owner;
import com.rje.petclinic.repositories.OwnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/owners")
public class OwnerController {

    private OwnerRepository ownerRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOwner(@RequestBody Owner owner) {
        ownerRepository.save(owner);
    }

    @GetMapping("/{ownerId}")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId) {
        return ownerRepository.findById(ownerId).get();
    }

    @GetMapping
    public List<Owner> findAll() {
        List<Owner> owners = new ArrayList<>();
        ownerRepository.findAll().forEach(owners::add);
        return owners;
    }

    @PutMapping(value = "/{ownerId}")
    public Owner updateOwner(@PathVariable("ownerId") Long ownerId, @RequestBody Owner owner) {
        Owner savedOwner = ownerRepository.save(owner);
        return savedOwner;
    }

}
