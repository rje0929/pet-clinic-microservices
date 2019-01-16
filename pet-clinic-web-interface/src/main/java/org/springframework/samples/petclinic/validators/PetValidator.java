package org.springframework.samples.petclinic.validators;

import org.springframework.lang.NonNull;
import org.springframework.samples.petclinic.dto.PetDTO;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

class PetValidator implements Validator {

    private static final String REQUIRED = "required";

    @Override
    public void validate(@NonNull Object obj, @NonNull Errors errors) {
        PetDTO pet = (PetDTO) obj;
        String name = pet.getName();
        // name validation
        if (!StringUtils.hasLength(name)) {
            errors.rejectValue("name", REQUIRED, REQUIRED);
        }

        // type validation
        if (pet.isNew() && pet.getPetType() == null) {
            errors.rejectValue("type", REQUIRED, REQUIRED);
        }

        // birth date validation
        if (pet.getBirthDate() == null) {
            errors.rejectValue("birthDate", REQUIRED, REQUIRED);
        }
    }

    /**
     * This Validator validates *just* Pet instances
     */
    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return PetDTO.class.isAssignableFrom(clazz);
    }


}
