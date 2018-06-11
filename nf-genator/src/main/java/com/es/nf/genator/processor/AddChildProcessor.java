package com.es.nf.genator.processor;

import com.es.nf.commons.services.genetic.GeneticServices;
import com.es.nf.commons.ws.Auth0RestTemplate;
import com.es.nf.domain.v1.BiologicalFile;
import com.es.nf.domain.v1.Personnage;
import com.es.nf.domain.v1.genetic.entity.HumanGenome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class AddChildProcessor implements DataProcessor {

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


    private int partyIdPere     = 1;
    private int partyIdMere     = 2;
    private String firstName    = "Nielsen";
    private String lastName     = "Eriksen";

    @Override
    public int process() {

        createPersonnage();
        return 0;
    }

    private void createPersonnage() {
        Personnage pers = new Personnage();

        pers.setAge(0);
        pers.setNom(lastName);
        pers.setPrenom(firstName);
        pers = addPerso(pers);

        BiologicalFile bfMere = getBiologicalFile (partyIdMere);
        BiologicalFile bfPere = getBiologicalFile (partyIdPere);

        BiologicalFile bfEnfant = new BiologicalFile();
        bfEnfant.setPartyId(pers.getPartyId());

        geneticServices.createChild(bfPere, bfMere, bfEnfant, genome);

        addBF(bfEnfant);

    }

    private BiologicalFile getBiologicalFile(int partyidMere) {
        BiologicalFile returnedFile = restTemplate.getForObject(bfURL + "/"+partyidMere, BiologicalFile.class);
        return returnedFile;
    }

    private Personnage addPerso(Personnage pers) {

        Personnage persoReturned = restTemplate.postForObject(persoURL, pers, Personnage.class);

        return persoReturned;
    }

    private BiologicalFile addBF(BiologicalFile bf) {

        BiologicalFile bfReturned = restTemplate.postForObject(bfURL, bf, BiologicalFile.class);

        return bfReturned;
    }
}
