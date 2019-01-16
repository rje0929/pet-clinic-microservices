package org.springframework.samples.petclinic.dto;

import lombok.Data;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import java.io.Serializable;
import java.util.*;

@Data
public class OwnerDTO implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String telephone;
    private Set<PetDTO> pets = new HashSet<>();

    private Set<PetDTO> getPetsInternal() {
        if (this.pets == null) {
            this.pets = new HashSet<>();
        }
        return this.pets;
    }

    protected void setPetsInternal(Set<PetDTO> pets) {
        this.pets = pets;
    }

    public List<PetDTO> getPets() {
        List<PetDTO> sortedPets = new ArrayList<>(getPetsInternal());
        PropertyComparator.sort(sortedPets,
            new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedPets);
    }

    public void addPet(PetDTO pet) {
        if (pet.isNew()) {
            getPetsInternal().add(pet);
        }
        pet.setOwner(this);
    }

    public PetDTO getPet(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (PetDTO pet : getPetsInternal()) {
            if (!ignoreNew || !pet.isNew()) {
                String compName = pet.getName();
                compName = compName.toLowerCase();
                if (compName.equals(name)) {
                    return pet;
                }
            }
        }
        return null;
    }

    public boolean isNew() {
        return this.id == null;
    }

}
