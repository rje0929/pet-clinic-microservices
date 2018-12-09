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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    public OwnerRepository ownerRepository;

    @InjectMocks
    public OwnerController ownerController;

    public List<Owner> ownerList;

    public Owner owner1;
    public Owner owner2;
    public Owner owner3;

    @BeforeEach
    void setUp() {
        ownerList = new ArrayList<>();

        owner1 = Owner.builder().id(1l).firstName("Randy").lastName("Moss").build();
        owner2 = Owner.builder().id(2l).firstName("Chris").lastName("Carter").build();
        owner3 = Owner.builder().id(3l).firstName("John").lastName("Randle").build();

        ownerList.add(owner1);
        ownerList.add(owner2);
        ownerList.add(owner3);
    }

    @Test
    void createOwner() {
        when(ownerRepository.save(ArgumentMatchers.any())).thenReturn(Owner.builder().id(14L).build());

        Owner savedOwner = ownerController.createOwner(owner1);

        assertNotNull(savedOwner);
        assertTrue(savedOwner.getId().equals(14L));
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
        assertTrue(owners.size() == 3);
    }

    @Test
    void updateOwner() {
        when(ownerRepository.save(ArgumentMatchers.any())).thenReturn(Owner.builder().id(35l).build());

        Owner savedOwner = ownerController.updateOwner(35L, owner1);

        assertNotNull(savedOwner);
        assertTrue(savedOwner.getId() == 35);
    }
}