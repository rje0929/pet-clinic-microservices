package org.springframework.samples.petclinic.formatters;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.clients.OwnerClient;
import org.springframework.samples.petclinic.dto.PetTypeDTO;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@Component
public class PetTypeFormatter implements Formatter<PetTypeDTO> {

    private OwnerClient ownerClient;


    @Autowired
    public PetTypeFormatter(OwnerClient ownerClient) {
        this.ownerClient = ownerClient;
    }

    @Override
    public String print(PetTypeDTO petType, Locale locale) {
        return petType.getName();
    }

    @Override
    public PetTypeDTO parse(String text, Locale locale) throws ParseException {
        Collection<PetTypeDTO> findPetTypes = ownerClient.findPetTypes();
        for (PetTypeDTO type : findPetTypes) {
            if (type.getName().equals(text)) {
                return type;
            }
        }
        throw new ParseException("type not found: " + text, 0);
    }

}
