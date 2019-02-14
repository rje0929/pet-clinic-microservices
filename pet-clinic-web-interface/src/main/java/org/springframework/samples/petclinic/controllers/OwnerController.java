package org.springframework.samples.petclinic.controllers;

import org.springframework.samples.petclinic.clients.OwnerClient;
import org.springframework.samples.petclinic.clients.VisitClient;
import org.springframework.samples.petclinic.dto.OwnerDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Controller
class OwnerController {

    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
    private final OwnerClient ownerClient;
    private final VisitClient visitClient;

    public OwnerController(OwnerClient ownerClient, VisitClient visitClient) {
        this.ownerClient = ownerClient;
        this.visitClient = visitClient;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/owners/new")
    public String initCreationForm(Map<String, Object> model) {
        OwnerDTO owner = new OwnerDTO();
        model.put("owner", owner);
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/owners/new")
    public String processCreationForm(@Valid OwnerDTO owner, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            OwnerDTO savedOwner = ownerClient.createOwner(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

    @GetMapping("/owners/find")
    public String initFindForm(Map<String, Object> model) {
        model.put("owner", new OwnerDTO());
        return "owners/findOwners";
    }

    @GetMapping("/owners")
    public String processFindForm(@Valid @ModelAttribute("owner") OwnerDTO owner, BindingResult result, Map<String, Object> model) {
        // allow parameterless GET request for /owners to return all records
        if (owner.getLastName() == null) {
            owner.setLastName(""); // empty string signifies broadest possible search
        }

        // find owners by last name
        List<OwnerDTO> results = ownerClient.findByLastNameLike("%" + owner.getLastName() + "%");
        if (results.isEmpty()) {
            // no owners found
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        } else if (results.size() == 1) {
            // 1 owner found
            OwnerDTO ownerDTO = results.get(0);
            return "redirect:/owners/" + ownerDTO.getId();
        } else {
            // multiple owners found
            model.put("selections", results);
            return "owners/ownersList";
        }
    }

    @GetMapping("/owners/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable("ownerId") Long ownerId, Model model) {
        model.addAttribute("owner", ownerClient.findOwner(ownerId));
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/owners/{ownerId}/edit")
    public String processUpdateOwnerForm(@Valid OwnerDTO owner, BindingResult result, @PathVariable("ownerId") int ownerId) {
        if (result.hasErrors()) {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            owner.setId((long) ownerId);
            ownerClient.updateOwner((long) ownerId, owner);
            return "redirect:/owners/{ownerId}";
        }
    }

    @GetMapping("/owners/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        OwnerDTO owner = ownerClient.findOwner(ownerId);
        owner.getPets().forEach(pet -> {
            pet.setVisits(new HashSet<>(visitClient.findVisitsByPetId(pet.getId())));
        });
        mav.addObject("owner", owner);
        return mav;
    }

}
