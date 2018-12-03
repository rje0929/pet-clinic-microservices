package com.rje.petclinic.repositories;

import com.rje.petclinic.model.Specialty;
import org.springframework.data.repository.CrudRepository;

public interface SpecialtyRepository extends CrudRepository<Specialty, Long> {
}
