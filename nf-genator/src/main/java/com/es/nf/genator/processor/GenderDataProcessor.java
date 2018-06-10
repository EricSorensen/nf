package com.es.nf.genator.processor;

import com.es.nf.commons.ws.Auth0RestTemplate;
import com.es.nf.domain.v1.BiologicalFile;
import com.es.nf.domain.v1.Personnage;
import com.es.nf.domain.v1.genetic.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class GenderDataProcessor implements DataProcessor{

    @Autowired
    private Auth0RestTemplate restTemplate;

    @Autowired
    private HumanGenome genome;

    @Value("${com.es.nf.genator.services.url.bfAdd}")
    private String bfURL;


    @Value("${com.es.nf.genator.services.url.persoList}")
    private String persoURL;

    private List<Integer> femmes = new ArrayList<Integer>();


    public GenderDataProcessor() {
        femmes.add(2);
        femmes.add(4);
        femmes.add(6);
    }

    @Override
    public int process() {

        // step 1 : get all the character with no parents
        List<Personnage> persoList = getPerso();

        AddGender(persoList);
        return 0;
    }

    private void AddGender(List<Personnage> persoList) {

        for (Personnage perso : persoList) {
            BiologicalFile bf = new BiologicalFile();
            bf.setPartyId(perso.getPartyId());
            GeneticCodeImpl gc = new GeneticCodeImpl();

            Gene gene = new GeneImpl();
            gene.setName("H-S");
            gene.setAlleleA(1);
            if (femmes.contains(bf.getPartyId())) {
                gene.setAlleleB(1);
            } else {
                gene.setAlleleB(0);
            }

            gc.addGene(gene, genome);
            bf.setGeneticCode(gc);

            addBF(bf);
        }
    }

    private BiologicalFile addBF(BiologicalFile bf) {

        BiologicalFile bfReturned = restTemplate.postForObject(getBfURL(), bf, BiologicalFile.class);

        return bfReturned;
    }

    private List<Personnage> getPerso() {
        ParameterizedTypeReference<List<Personnage>> listOfPersonnage = new ParameterizedTypeReference<List<Personnage>>() {};

        ResponseEntity<List<Personnage>> response = restTemplate.exchange(getPersoURL(),HttpMethod.GET, listOfPersonnage);

        return response.getBody();
    }


    public String getBfURL() {
        return bfURL;
    }

    public void setBfURL(String bfURL) {
        this.bfURL = bfURL;
    }

    public String getPersoURL() {
        return persoURL;
    }
}
