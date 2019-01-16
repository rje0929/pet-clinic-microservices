package org.springframework.samples.petclinic.controllers;

import org.springframework.samples.petclinic.clients.OwnerClient;
import org.springframework.samples.petclinic.dto.OwnerDTO;
import org.springframework.samples.petclinic.dto.PetDTO;
import org.springframework.samples.petclinic.dto.PetTypeDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
class PetController {

    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";

    private final OwnerClient ownerClient;

    public PetController(OwnerClient ownerClient) {
        this.ownerClient = ownerClient;
    }

    @ModelAttribute("types")
    public Collection<PetTypeDTO> populatePetTypes() {
        return ownerClient.findPetTypes();
    }

    @ModelAttribute("owner")
    public OwnerDTO findOwner(@PathVariable("ownerId") Long ownerId) {
        return ownerClient.findOwner(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/pets/new")
    public String initCreationForm(@ModelAttribute("owner") OwnerDTO owner, ModelMap model) {
        PetDTO pet = new PetDTO();
        owner.addPet(pet);
        pet.setOwner(owner);
        model.put("pet", pet);
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/new")
    public String processCreationForm(@ModelAttribute("owner") OwnerDTO owner, @Valid PetDTO pet, BindingResult result, ModelMap model) {
        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null) {
            result.rejectValue("name", "duplicate", "already exists");
        }
        owner.addPet(pet);
        if (result.hasErrors()) {
            model.put("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        } else {
            ownerClient.createPet(owner.getId(), pet);
            return "redirect:/owners/{ownerId}";
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(@ModelAttribute("owner") OwnerDTO owner, @PathVariable("petId") int petId, ModelMap model) {
        PetDTO pet = ownerClient.findPetById((long) petId);
        pet.setOwner(owner);
        model.put("pet", pet);
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(@Valid PetDTO pet, BindingResult result, @ModelAttribute("owner") OwnerDTO owner, ModelMap model) {
        if (result.hasErrors()) {
            pet.setOwner(owner);
            model.put("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        } else {
            owner.addPet(pet);
            ownerClient.savePet(owner.getId(), pet.getId(), pet);
            return "redirect:/owners/{ownerId}";
        }
    }

}
