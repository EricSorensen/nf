package com.es.nf.genator.processor;

import com.es.nf.commons.ws.Auth0RestTemplate;
import com.es.nf.domain.v1.BiologicalFile;
import com.es.nf.domain.v1.Personnage;
import com.es.nf.domain.v1.genetic.entity.Gene;
import com.es.nf.domain.v1.genetic.entity.GeneImpl;
import com.es.nf.domain.v1.genetic.entity.HumanGenome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class AddGeneHairDataProcessor implements DataProcessor {

    @Autowired
    private Auth0RestTemplate restTemplate;

    @Autowired
    private HumanGenome genome;

    @Value("${com.es.nf.genator.services.url.bfListColon}")
    private String bfURL;

    @Value("${com.es.nf.genator.services.url.bfUpdate}")
    private String bfURLUpdate;

    @Override
    public int process() {

        //retrieve list of colons
        List<BiologicalFile> colons = getColons();

        for (BiologicalFile bf : colons) {
            Gene gene = new GeneImpl();
            gene.setName("H-R");

            switch (bf.getPartyId()){
                case 1 :
                    gene.setAlleleA(0);
                    gene.setAlleleB(0);
                    break;
                case 2 :
                    gene.setAlleleA(0);
                    gene.setAlleleB(0);
                    break;
                case 3 :
                    gene.setAlleleA(0);
                    gene.setAlleleB(0);
                    break;
                case 4 :
                    gene.setAlleleA(1);
                    gene.setAlleleB(1);
                    break;
                case 5 :
                    gene.setAlleleA(1);
                    gene.setAlleleB(0);
                    break;
                case 6 :
                    gene.setAlleleA(0);
                    gene.setAlleleB(0);
                    break;
            }

            bf.getGeneticCode().addGene(gene, genome);
            Gene gene2 = new GeneImpl();
            gene2.setName("H-BB");

            switch (bf.getPartyId()){
                case 1 :
                    gene2.setAlleleA(8);
                    gene2.setAlleleB(7);
                    break;
                case 2 :
                    gene2.setAlleleA(1);
                    gene2.setAlleleB(2);
                    break;
                case 3 :
                    gene2.setAlleleA(3);
                    gene2.setAlleleB(2);
                    break;
                case 4 :
                    gene2.setAlleleA(4);
                    gene2.setAlleleB(5);
                    break;
                case 5 :
                    gene2.setAlleleA(4);
                    gene2.setAlleleB(3);
                    break;
                case 6 :
                    gene2.setAlleleA(6);
                    gene2.setAlleleB(9);
                    break;
            }

            bf.getGeneticCode().addGene(gene2, genome);
            updateBF(bf);
        }

        return 0;
    }

    private List<BiologicalFile> getColons() {
        ParameterizedTypeReference<List<BiologicalFile>> listOfBiologicalFile = new ParameterizedTypeReference<List<BiologicalFile>>() {};

        ResponseEntity<List<BiologicalFile>> response = restTemplate.exchange(getBfURL(),HttpMethod.GET, null,listOfBiologicalFile);

        return response.getBody();
    }

    public String getBfURL() {
        return bfURL;
    }

    private void updateBF (BiologicalFile bf) {
        restTemplate.put(getBfURLUpdate()+"/" + bf.getPartyId(), bf, BiologicalFile.class);
    }

    public String getBfURLUpdate() {
        return bfURLUpdate;
    }
}
