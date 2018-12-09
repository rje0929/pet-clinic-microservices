package org.springframework.samples.petclinic.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SpecialtyDTO implements Serializable {

    private Long id;
    private String description;

}
