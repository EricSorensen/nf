package com.es.nf.services.v1.controller;

import com.es.nf.services.v1.entity.Personnage;
import com.es.nf.services.v1.repository.PersonnageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/v1")
public class PersonnageController {

    @Autowired
    private PersonnageRepository repository;

    @GetMapping("/perso/{partyId}")
    @PreAuthorize("#oauth2.hasScope('personnage.read')")
    public Personnage getPerso(@PathVariable(value = "partyId", required = true)int partyId){

        //Nothing to do except return the value found in repository
        Personnage pers = new Personnage();
        pers.setNom("Dummy");
        return pers;
    }

    @GetMapping("/personnage/{partyId}")
    @PreAuthorize("#oauth2.hasScope('personnage.read')")
    public Personnage getPersonnage(@PathVariable(value = "partyId", required = true)int partyId){

        //Nothing to do except return the value found in repository
        return repository.findByPartyId(partyId);
    }

    @GetMapping("/personnages")
    @PreAuthorize("#oauth2.hasScope('personnage.read')")
    public List<Personnage> getPersonnages() {
        //Nothing to do except return the value found in repository
        return repository.findAll();
    }
}
