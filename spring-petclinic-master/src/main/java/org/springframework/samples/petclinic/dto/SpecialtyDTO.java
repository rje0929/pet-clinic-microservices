package org.springframework.samples.petclinic.dto;

import java.io.Serializable;

public class SpecialtyDTO implements Serializable {

    private Long id;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
