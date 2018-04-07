package com.es.nf.batch.dto;

import com.es.nf.domain.v1.entity.Personnage;

public class PersonnageDTO {

    private Personnage personnage;

    public Personnage getPersonnage() {
        return personnage;
    }

    public void setPersonnage(Personnage personnage) {
        this.personnage = personnage;
    }
}
