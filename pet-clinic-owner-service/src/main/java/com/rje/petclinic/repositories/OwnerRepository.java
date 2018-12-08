package com.rje.petclinic.repositories;

import com.rje.petclinic.model.Owner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);

    @Query("SELECT o FROM Owner o, Pet p WHERE o.id = p.owner.id AND p.id = :pet_id")
    Owner findOwnerByPetId(@Param("pet_id") Long petId);

}
