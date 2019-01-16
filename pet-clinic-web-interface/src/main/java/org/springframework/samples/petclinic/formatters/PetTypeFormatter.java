package org.springframework.samples.petclinic.formatters;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.lang.NonNull;
import org.springframework.samples.petclinic.clients.OwnerClient;
import org.springframework.samples.petclinic.dto.PetTypeDTO;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@Component
public class PetTypeFormatter implements Formatter<PetTypeDTO> {

    private final OwnerClient ownerClient;

    @Autowired
    public PetTypeFormatter(OwnerClient ownerClient) {
        this.ownerClient = ownerClient;
    }

    @Override
    @NonNull
    public String print(@NonNull PetTypeDTO petType, @NonNull Locale locale) {
        return petType.getName();
    }

    @Override
    @NonNull
    public PetTypeDTO parse(@NonNull String text, @NonNull Locale locale) throws ParseException {
        Collection<PetTypeDTO> findPetTypes = ownerClient.findPetTypes();
        for (PetTypeDTO type : findPetTypes) {
            if (type.getName().equals(text)) {
                return type;
            }
        }
        throw new ParseException("type not found: " + text, 0);
    }

}
