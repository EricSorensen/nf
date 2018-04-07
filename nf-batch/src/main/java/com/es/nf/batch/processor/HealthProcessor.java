package com.es.nf.batch.processor;

import com.es.nf.batch.dto.PersonnageDTO;
import org.springframework.batch.item.ItemProcessor;

public class HealthProcessor implements ItemProcessor<PersonnageDTO,PersonnageDTO> {

    @Override
    public PersonnageDTO process(PersonnageDTO dto) throws Exception {

        System.out.println("Traitement Health perso " + dto.getPersonnage().getPrenom());
        return dto;
    }
}
