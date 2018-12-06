package org.springframework.samples.petclinic.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class PetDTO implements Serializable {

    private Long id;
    private String name;
    private PetTypeDTO petType;
    private LocalDate birthDate;
    private Set<VisitDTO> visits = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetTypeDTO getPetType() {
        return petType;
    }

    public void setPetType(PetTypeDTO petType) {
        this.petType = petType;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Set<VisitDTO> getVisits() {
        return visits;
    }

    public void setVisits(Set<VisitDTO> visits) {
        this.visits = visits;
    }
}
