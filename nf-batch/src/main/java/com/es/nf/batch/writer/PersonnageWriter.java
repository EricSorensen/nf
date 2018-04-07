package com.es.nf.batch.writer;

import com.es.nf.batch.dto.PersonnageDTO;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class PersonnageWriter implements ItemWriter<PersonnageDTO> {

    @Override
    public void write(List<? extends PersonnageDTO> list) throws Exception {

    }
}
