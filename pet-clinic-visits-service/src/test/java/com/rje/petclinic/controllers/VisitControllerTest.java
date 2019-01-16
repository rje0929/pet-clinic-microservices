package com.rje.petclinic.controllers;

import com.rje.petclinic.model.Visit;
import com.rje.petclinic.repositories.VisitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @Mock
    private VisitRepository visitRepository;

    @InjectMocks
    private VisitController visitController;

    private List<Visit> visitList;

    private Visit visit1;
    private Visit visit2;
    private Visit visit3;

    @BeforeEach
    void setUp() {
        visitList = new ArrayList<>();

        visit1 = Visit.builder().id(1L).description("Hair cut").visitDate(LocalDate.of(2018, 5, 10)).petId(3L).build();
        visit2 = Visit.builder().id(2L).description("Checkup").visitDate(LocalDate.of(2018, 3, 20)).petId(2L).build();
        visit3 = Visit.builder().id(3L).description("Beard wax").visitDate(LocalDate.of(2018, 12, 1)).petId(1L).build();

        visitList.add(visit1);
        visitList.add(visit2);
        visitList.add(visit3);
    }

    @Test
    void createVisit() {
        when(visitRepository.save(ArgumentMatchers.any())).thenReturn(Visit.builder().id(25L).build());

        Visit savedVisit = visitController.createVisit(visit1, 4L);

        assertNotNull(savedVisit);
        assertEquals(25L, (long) savedVisit.getId());
    }

    @Test
    void findVisitsByPetId() {
        when(visitRepository.findByPetId(anyLong())).thenReturn(visitList);

        List<Visit> visits = visitController.findVisitsByPetId(5L);

        assertNotNull(visits);
        assertEquals(3, visits.size());
    }

    @Test
    void findVisitsByPetIds() {
        when(visitRepository.findByPetIdIn(anyList())).thenReturn(visitList);

        List<Visit> visits = visitController.findVisitsByPetIds(Arrays.asList(1L, 2L, 3L));

        assertNotNull(visits);
        assertEquals(3, visits.size());
    }
}