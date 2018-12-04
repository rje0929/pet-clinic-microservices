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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @Mock
    private VisitRepository visitRepository;

    @InjectMocks
    private VisitController visitController;

    public List<Visit> visitList;

    public Visit visit1;
    public Visit visit2;
    public Visit visit3;

    @BeforeEach
    void setUp() {
        visitList = new ArrayList<>();

        visit1 = Visit.builder().id(1l).description("Hair cut").visitDate(LocalDate.of(2018, 5, 10)).petId(3l).build();
        visit2 = Visit.builder().id(2l).description("Checkup").visitDate(LocalDate.of(2018, 3, 20)).petId(2l).build();
        visit3 = Visit.builder().id(3l).description("Beard wax").visitDate(LocalDate.of(2018, 12, 1)).petId(1l).build();

        visitList.add(visit1);
        visitList.add(visit2);
        visitList.add(visit3);
    }

    @Test
    void createVisit() {
        when(visitRepository.save(ArgumentMatchers.any())).thenReturn(Visit.builder().id(25l).build());

        Visit savedVisit = visitController.createVisit(visit1, 4l);

        assertNotNull(savedVisit);
        assertTrue(savedVisit.getId() == 25l);
    }

    @Test
    void findVisitsByPetId() {
        when(visitRepository.findByPetId(anyLong())).thenReturn(visitList);

        List<Visit> visits = visitController.findVisitsByPetId(5l);

        assertNotNull(visits);
        assertTrue(visits.size() == 3);
    }

    @Test
    void findVisitsByPetIds() {
        when(visitRepository.findByPetIdIn(anyList())).thenReturn(visitList);

        List<Visit> visits = visitController.findVisitsByPetIds(Arrays.asList(1l, 2l, 3l));

        assertNotNull(visits);
        assertTrue(visits.size() == 3);
    }
}