package org.springframework.samples.petclinic.controllers;

import org.springframework.samples.petclinic.clients.OwnerClient;
import org.springframework.samples.petclinic.clients.VisitClient;
import org.springframework.samples.petclinic.dto.OwnerDTO;
import org.springframework.samples.petclinic.dto.PetDTO;
import org.springframework.samples.petclinic.dto.VisitDTO;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Controller
class VisitController {

    private final VisitClient visitClient;
    private final OwnerClient ownerClient;

    public VisitController(VisitClient visitClient, OwnerClient ownerClient) {
        this.visitClient = visitClient;
        this.ownerClient = ownerClient;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("visit")
    public VisitDTO loadPetWithVisit(@PathVariable("petId") int petId, Map<String, Object> model) {
        PetDTO pet = ownerClient.findPetById((long) petId);
        OwnerDTO owner = ownerClient.findOwnerByPetId((long) petId);
        pet.setOwner(owner);

        VisitDTO visit = new VisitDTO();
        List<VisitDTO> visits = visitClient.findVisitsByPetId((long) petId);
        if (visits.size() == 0) {
            pet.addVisit(visit);
        } else {
            pet.setVisits(new HashSet<>(visits));
        }

        model.put("pet", pet);
        return visit;
    }

    // Spring MVC calls method loadPetWithVisit(...) before initNewVisitForm is called
    @GetMapping("/owners/*/pets/{petId}/visits/new")
    public String initNewVisitForm(@PathVariable("petId") int petId, Map<String, Object> model) {
        return "pets/createOrUpdateVisitForm";
    }

    // Spring MVC calls method loadPetWithVisit(...) before processNewVisitForm is called
    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String processNewVisitForm(@Valid VisitDTO visit, @PathVariable("petId") Long petId, BindingResult result) {
        if (result.hasErrors()) {
            return "pets/createOrUpdateVisitForm";
        } else {
            visitClient.createVisit(visit, petId);
            return "redirect:/owners/{ownerId}";
        }
    }

}
