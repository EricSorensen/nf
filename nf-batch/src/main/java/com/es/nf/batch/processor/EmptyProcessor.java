package com.es.nf.batch.processor;

import com.es.nf.batch.dto.PersonnageDTO;
import org.springframework.batch.item.ItemProcessor;

public class EmptyProcessor implements ItemProcessor<PersonnageDTO,PersonnageDTO> {
    @Override
    public PersonnageDTO process(PersonnageDTO personnageDTO) throws Exception {
        return personnageDTO;
    }
}
