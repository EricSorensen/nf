package com.es.nf.batch.configuration;

import com.es.nf.batch.dto.PersonnageDTO;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PersonnageListBatch {

    private List<PersonnageDTO> items;
    private int nextItemIndex;

    public PersonnageListBatch() {
        // initialize iteration process
        items = new ArrayList<PersonnageDTO>();
        nextItemIndex = 0;
    }

    public void add(PersonnageDTO pers) {
        items.add(pers);

    }

    public synchronized PersonnageDTO getNext() {

        PersonnageDTO ret=null;

        if (nextItemIndex < items.size()) {
            ret= items.get(nextItemIndex);
            nextItemIndex++;
        }
        return ret;
    }
}
