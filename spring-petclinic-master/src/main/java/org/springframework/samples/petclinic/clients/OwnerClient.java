package org.springframework.samples.petclinic.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.samples.petclinic.dto.OwnerDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("owner-service")
public interface OwnerClient {

    @RequestMapping(method = RequestMethod.GET, value = "/owners")
    List<OwnerDTO> findAllOwners();

    @RequestMapping(method = RequestMethod.GET, value = "/owners/findByLastName/{name}")
    OwnerDTO findByLastName(@PathVariable String name);

    @RequestMapping(method = RequestMethod.GET, value = "/owners/findByLastNameLike/{name}")
    List<OwnerDTO> findByLastNameLike(@PathVariable String name);

    @RequestMapping(method = RequestMethod.GET, value = "/owners/{ownerId}")
    OwnerDTO findOwner(@PathVariable("ownerId") Long ownerId);

    @RequestMapping(method = RequestMethod.PUT, value = "/owners/{ownerId}")
    OwnerDTO updateOwner(@PathVariable("ownerId") Long ownerId, @RequestBody OwnerDTO owner);

    @RequestMapping(method = RequestMethod.POST, value = "/owners")
    OwnerDTO createOwner(@RequestBody OwnerDTO owner);

}
