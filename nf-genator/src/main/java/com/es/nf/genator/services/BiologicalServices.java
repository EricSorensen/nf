package com.es.nf.genator.services;

import com.es.nf.commons.ws.Auth0RestTemplate;
import com.es.nf.domain.v1.BiologicalFile;
import com.es.nf.domain.v1.genetic.entity.HumanGenome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BiologicalServices {

    @Autowired
    private Auth0RestTemplate restTemplate;

    @Autowired
    private HumanGenome genome;

    @Value("${com.es.nf.genator.services.url.bfListParent}")
    private String bfURLParent;

    @Value("${com.es.nf.genator.services.url.bfBase}")
    private String bfURL;


    @Cacheable("BiologicalFile")
    public List<BiologicalFile> getColonsList() {

        ParameterizedTypeReference<List<BiologicalFile>> listOfBiologicalFile = new ParameterizedTypeReference<List<BiologicalFile>>() {};
        ResponseEntity<List<BiologicalFile>> response = restTemplate.exchange(bfURLParent + "/0",HttpMethod.GET, null,listOfBiologicalFile);

        return response.getBody();
    }

    @Cacheable("BiologicalFile")
    public List<BiologicalFile> getChildList(List<BiologicalFile> bfParentList) {

        String listOfPartyIdParent = "";

        if (bfParentList.size()>0) {
            listOfPartyIdParent = "/" + bfParentList.get(0);
        }

        for (int i=1; i<bfParentList.size(); i++) {
            listOfPartyIdParent = listOfPartyIdParent + "," + bfParentList.get(i);
        }

        ParameterizedTypeReference<List<BiologicalFile>> listOfBiologicalFile = new ParameterizedTypeReference<List<BiologicalFile>>() {};
        ResponseEntity<List<BiologicalFile>> response = restTemplate.exchange(bfURLParent + listOfPartyIdParent,HttpMethod.GET, null,listOfBiologicalFile);

        return response.getBody();
    }


    @Cacheable("BiologicalFile")
    public BiologicalFile getBiologicalFile(int partyId) {
        BiologicalFile returnedFile = restTemplate.getForObject(bfURL + "/"+partyId, BiologicalFile.class);
        return returnedFile;
    }

    public List<BiologicalFile> getAllBFPerPartyId () {

        ParameterizedTypeReference<List<BiologicalFile>> listOfBiologicalFile = new ParameterizedTypeReference<List<BiologicalFile>>() {};
        ResponseEntity<List<BiologicalFile>> response = restTemplate.exchange(bfURL + "?sort=partyId",HttpMethod.GET, null,listOfBiologicalFile);

        return response.getBody();

    }
}
