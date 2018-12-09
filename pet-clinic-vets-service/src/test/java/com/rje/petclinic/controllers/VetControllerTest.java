package com.rje.petclinic.controllers;

import com.rje.petclinic.model.Vet;
import com.rje.petclinic.repositories.VetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {

    @Mock
    public VetRepository vetRepository;

    @InjectMocks
    public VetController vetController;

    public List<Vet> vetList;

    public Vet vet1;
    public Vet vet2;
    public Vet vet3;

    @BeforeEach
    void setUp() {
        vetList = new ArrayList<>();

        vet1 = Vet.builder().id(1L).firstName("Teddy").lastName("Bridgewater").build();
        vet2 = Vet.builder().id(2L).firstName("Randall").lastName("Cunningham").build();
        vet3 = Vet.builder().id(3L).firstName("Kirk").lastName("Cousins").build();

        vetList.add(vet1);
        vetList.add(vet2);
        vetList.add(vet3);
    }

    @Test
    void findAllVets() {
        when(vetRepository.findAll()).thenReturn(vetList);

        List<Vet> vets = vetController.findAllVets();

        assertNotNull(vets);
        assertTrue(vets.size() == 3);
    }
}