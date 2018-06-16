package com.es.nf.genator.visitor;

import com.es.nf.commons.services.genetic.GenealogyVisitorImpl;
import com.es.nf.commons.ws.Auth0RestTemplate;
import com.es.nf.domain.v1.BiologicalFile;
import com.es.nf.domain.v1.genetic.entity.HumanGenome;
import com.es.nf.genator.services.BiologicalServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class BiologicalFileVisitor extends GenealogyVisitorImpl {

    @Autowired
    private BiologicalServices bfServices;

    @Override
    public List<BiologicalFile> getAllBFListPerPartyId() {
        return bfServices.getColonsList();
    }

    protected BiologicalFile geBiologicalFile (int partyId){
        return bfServices.getBiologicalFile(partyId);
    }

}
