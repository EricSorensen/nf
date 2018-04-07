package com.es.nf.batch.reader;

import com.es.nf.batch.Security.Auth0RestTemplate;
import com.es.nf.batch.configuration.PersonnageListBatch;
import com.es.nf.batch.dto.PersonnageDTO;
import com.es.nf.domain.v1.entity.Personnage;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonnageReader implements ItemReader<PersonnageDTO> {

    @Autowired
    private PersonnageListBatch persList;

    @Autowired
    private Auth0RestTemplate restTemplate;

    @Value("${com.es.nf.batch.oauth2.services.url.personnagesList}")
    private String personnagesURL;


    @Override
    public synchronized PersonnageDTO read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        ParameterizedTypeReference<List<Personnage>> listOfPersonnages = new ParameterizedTypeReference<List<Personnage>>() {};

        ResponseEntity<List<Personnage>> response = restTemplate.exchange(getPersonnagesURL(),HttpMethod.GET,listOfPersonnages);

        for (Personnage pers : response.getBody()) {
            PersonnageDTO dto = new PersonnageDTO();

            dto.setPersonnage(pers);
            persList.add(dto);
        }


        return null;
    }

    private String getPersonnagesURL() {
        return personnagesURL;
    }

}
