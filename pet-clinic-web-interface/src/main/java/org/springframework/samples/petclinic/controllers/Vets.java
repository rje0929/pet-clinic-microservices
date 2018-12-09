package org.springframework.samples.petclinic.controllers;

import org.springframework.samples.petclinic.dto.VetDTO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Vets {

    private List<VetDTO> vets;

    @XmlElement
    public List<VetDTO> getVetList() {
        if (vets == null) {
            vets = new ArrayList<>();
        }
        return vets;
    }

}
