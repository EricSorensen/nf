package com.es.nf.genator.processor;

import com.es.nf.commons.services.genetic.GeneticServices;
import com.es.nf.commons.ws.Auth0RestTemplate;
import com.es.nf.domain.v1.genetic.entity.HumanGenome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class AddGeneToColony implements DataProcessor {

    @Autowired
    private Auth0RestTemplate restTemplate;

    @Autowired
    private GeneticServices geneticServices;

    @Autowired
    private HumanGenome genome;

    @Value("${com.es.nf.genator.services.url.persoBase}")
    private String persoURL;

    @Value("${com.es.nf.genator.services.url.bfBase}")
    private String bfURL;

    @Override
    public int process() {
        return 0;
    }
}
