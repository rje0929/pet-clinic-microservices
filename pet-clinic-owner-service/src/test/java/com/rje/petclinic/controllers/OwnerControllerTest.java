package com.rje.petclinic.controllers;

import com.rje.petclinic.model.Owner;
import com.rje.petclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private OwnerController ownerController;

    private List<Owner> ownerList;

    private Owner owner1;
    private Owner owner2;
    private Owner owner3;

    @BeforeEach
    void setUp() {
        ownerList = new ArrayList<>();

        owner1 = Owner.builder().id(1L).firstName("Randy").lastName("Moss").build();
        owner2 = Owner.builder().id(2L).firstName("Chris").lastName("Carter").build();
        owner3 = Owner.builder().id(3L).firstName("John").lastName("Randle").build();

        ownerList.add(owner1);
        ownerList.add(owner2);
        ownerList.add(owner3);
    }

    @Test
    void createOwner() {
        when(ownerRepository.save(ArgumentMatchers.any())).thenReturn(Owner.builder().id(14L).build());

        Owner savedOwner = ownerController.createOwner(owner1);

        assertNotNull(savedOwner);
        assertEquals(14L, (long) savedOwner.getId());
    }

    @Test
    void findOwner() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(owner1));

        Owner owner = ownerController.findOwner(1L);

        assertNotNull(owner);
        assertTrue(owner.getFirstName().equalsIgnoreCase("Randy"));
        assertTrue(owner.getLastName().equalsIgnoreCase("Moss"));
    }

    @Test
    void findAllOwners() {
        when(ownerRepository.findAll()).thenReturn(ownerList);

        List<Owner> owners = ownerController.findAllOwners();

        assertNotNull(owners);
        assertEquals(3, owners.size());
    }

    @Test
    void updateOwner() {
        when(ownerRepository.save(ArgumentMatchers.any())).thenReturn(Owner.builder().id(35L).build());

        Owner savedOwner = ownerController.updateOwner(35L, owner1);

        assertNotNull(savedOwner);
        assertEquals(35, (long) savedOwner.getId());
    }
}