package org.springframework.samples.petclinic.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Data
public class PetDTO implements Serializable {

    private Long id;
    private String name;
    private PetTypeDTO petType;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private Set<VisitDTO> visits = new HashSet<>();
    @JsonIgnore
    private OwnerDTO owner;

    private Set<VisitDTO> getVisitsInternal() {
        if (this.visits == null) {
            this.visits = new HashSet<>();
        }
        return this.visits;
    }

    protected void setVisitsInternal(Set<VisitDTO> visits) {
        this.visits = visits;
    }

    public List<VisitDTO> getVisits() {
        List<VisitDTO> sortedVisits = new ArrayList<>(getVisitsInternal());
        PropertyComparator.sort(sortedVisits,
            new MutableSortDefinition("date", false, false));
        return Collections.unmodifiableList(sortedVisits);
    }

    public void addVisit(VisitDTO visit) {
        getVisitsInternal().add(visit);
        visit.setPetId(this.getId());
    }

    public boolean isNew() {
        return this.id == null;
    }

}
