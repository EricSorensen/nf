package com.es.nf.batch.reader;

import com.es.nf.batch.configuration.PersonnageListBatch;
import com.es.nf.batch.dto.PersonnageDTO;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HealthReader implements ItemReader<PersonnageDTO> {

    @Autowired
    private PersonnageListBatch persList;

    @Override
    public PersonnageDTO read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return persList.getNext();
    }
}
