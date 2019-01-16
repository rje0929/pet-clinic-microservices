package org.springframework.samples.petclinic.dto;

import lombok.Data;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.*;

@Data
public class VetDTO implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private Set<SpecialtyDTO> specialties = new HashSet<>();

    private Set<SpecialtyDTO> getSpecialtiesInternal() {
        if (this.specialties == null) {
            this.specialties = new HashSet<>();
        }
        return this.specialties;
    }

    protected void setSpecialtiesInternal(Set<SpecialtyDTO> specialties) {
        this.specialties = specialties;
    }

    @XmlElement
    public List<SpecialtyDTO> getSpecialties() {
        List<SpecialtyDTO> sortedSpecs = new ArrayList<>(getSpecialtiesInternal());
        PropertyComparator.sort(sortedSpecs,
            new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedSpecs);
    }

    public int getNrOfSpecialties() {
        return getSpecialtiesInternal().size();
    }

    public void addSpecialty(SpecialtyDTO specialty) {
        getSpecialtiesInternal().add(specialty);
    }
}
