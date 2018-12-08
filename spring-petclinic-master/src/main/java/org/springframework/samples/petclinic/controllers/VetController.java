package org.springframework.samples.petclinic.controllers;

import org.springframework.samples.petclinic.clients.VetClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
class VetController {

    private final VetClient vetClient;

    public VetController(VetClient vetClient) {
        this.vetClient = vetClient;
    }

    @GetMapping("/vets.html")
    public String showVetList(Map<String, Object> model) {
        Vets vets = new Vets();
        vets.getVetList().addAll(vetClient.findAllVets());
        model.put("vets", vets);
        return "vets/vetList";
    }

    @GetMapping({"/vets"})
    public @ResponseBody
    Vets showResourcesVetList() {
        Vets vets = new Vets();
        vets.getVetList().addAll(vetClient.findAllVets());
        return vets;
    }

}
