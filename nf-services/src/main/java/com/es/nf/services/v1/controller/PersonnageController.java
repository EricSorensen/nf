package com.es.nf.services.v1.controller;

import com.es.nf.domain.v1.Personnage;
import com.es.nf.services.v1.entity.BiologicalFileDB;
import com.es.nf.services.v1.entity.PersonnageDB;
import com.es.nf.services.v1.repository.PersonnageRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/v1")
public class PersonnageController {

    private static final Logger log = LoggerFactory.getLogger(PersonnageController.class);

    @Autowired
    private PersonnageRepository repository;

    @GetMapping("/perso/{partyId}")
    @PreAuthorize("#oauth2.hasScope('personnage.read')")
    public Personnage getPerso(@PathVariable(value = "partyId", required = true)int partyId){

        log.debug("call of /perso/"+ partyId);

        //Nothing to do except return the value found in repository
        PersonnageDB pers = new PersonnageDB();
        pers.setNom("Dummy");
        return pers;
    }

    @GetMapping("/personnages/{partyId}")
    @PreAuthorize("#oauth2.hasScope('personnage.read')")
    public Personnage getPersonnage(@PathVariable(value = "partyId", required = true)int partyId){

        log.debug("call of /personnage/"+ partyId);

        //Nothing to do except return the value found in repository
        return repository.findByPartyId(partyId);
    }

    @GetMapping("/personnages")
    @PreAuthorize("#oauth2.hasScope('personnage.read')")
    public List<PersonnageDB> getPersonnages() {
        //Nothing to do except return the value found in repository
        log.debug("call of /personnages");
        return repository.findAll();
    }

    @PostMapping("/personnages")
    @ResponseBody
    @PreAuthorize("#oauth2.hasScope('personnage.write')")
    public synchronized PersonnageDB createPersonnage(@RequestBody PersonnageDB pPerso){

        log.debug("call of POST /personnages/");

        // Find the last partyId
        // set the maxPartyId + 1 as the new partyid
        PersonnageDB maxPartyIdPerso = repository.findFirstByOrderByPartyIdDesc();
        pPerso.setPartyId(maxPartyIdPerso.getPartyId() + 1);

        //Create the character
        return repository.insert(pPerso);
    }
}
