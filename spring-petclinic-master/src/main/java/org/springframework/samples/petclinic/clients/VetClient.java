package org.springframework.samples.petclinic.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.samples.petclinic.dto.VetDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("vets-service")
public interface VetClient {

    @RequestMapping(method = RequestMethod.GET, value = "/vets")
    List<VetDTO> findAllVets();
}
