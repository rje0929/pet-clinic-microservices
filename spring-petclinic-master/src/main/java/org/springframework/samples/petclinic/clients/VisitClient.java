package org.springframework.samples.petclinic.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.samples.petclinic.dto.VisitDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("visits-service")
public interface VisitClient {

    @RequestMapping(method = RequestMethod.POST, value = "owners/*/pets/{petId}/visits")
    VisitDTO createVisit(@RequestBody VisitDTO visit, @PathVariable("petId") Long petId);

    @RequestMapping(method = RequestMethod.GET, value = "owners/*/pets/{petId}/visits")
    List<VisitDTO> findVisitsByPetId(@PathVariable("petId") Long petId);

    @RequestMapping(method = RequestMethod.GET, value = "pets/visits")
    List<VisitDTO> findVisitsByPetIds(@RequestParam("petId") List<Long> petIds);

}
