package com.es.nf.services.v1.controller;



import com.es.nf.domain.v1.BiologicalFile;
import com.es.nf.services.v1.entity.BiologicalFileDB;
import com.es.nf.services.v1.repository.BiologicalFileRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class BiologicalFileController {

    private static final Logger log = LoggerFactory.getLogger(BiologicalFileController.class);

    @Autowired
    private BiologicalFileRepository repository;

    @GetMapping("/biologicalfiles/{partyId}")
    @PreAuthorize("#oauth2.hasScope('biological.read')")
    public BiologicalFile getBiologicalFile(@PathVariable(value = "partyId", required = true)int partyId){

        log.debug("call of /biologicalfile/"+ partyId);

        //Nothing to do except return the value found in repository
        BiologicalFileDB returnedValue = repository.findByPartyId(partyId);
        return returnedValue;
    }

    @GetMapping("/biologicalfiles/parents/{partyIds}")
    @ResponseBody
    @PreAuthorize("#oauth2.hasScope('biological.read')")
    public List<BiologicalFileDB> getBiologicalFilePerParent(@PathVariable(value = "partyIds", required = false) Integer[] partyIds){

        List<Integer> partyParents = Arrays.asList(partyIds);

        log.debug("call of /biologicalfiles/parents/"+ partyParents);

        //Nothing to do except return the value found in repository
        List<BiologicalFileDB>returnedValue = repository.findByPartyIdPereInOrPartyIdMereIn(partyParents, partyParents);
        return returnedValue;
    }

    @PostMapping("/biologicalfiles")
    @ResponseBody
    @PreAuthorize("#oauth2.hasScope('biological.write')")
    public BiologicalFile createBiologicalFile(@RequestBody BiologicalFileDB pFile){

        log.debug("call of POST /biologicalfile/");

        //Create the Biological file
        return repository.insert(pFile);
    }


    @PutMapping("/biologicalfiles/{partyId}")
    @ResponseBody
    @PreAuthorize("#oauth2.hasScope('biological.write')")
    public BiologicalFile updateBiologicalFile(@PathVariable(value = "partyId", required = true)int partyId, @RequestBody BiologicalFileDB pFile){

        log.debug("call of PUT /biologicalfile/"+ partyId);

        // Get the BF objectid associated with the partyID
        // and set it into the object to serialize
        BiologicalFileDB updatedBF = repository.findByPartyId(partyId);
        pFile.setId(updatedBF.getId());


        //Nothing to do except return the value found in repository
        BiologicalFileDB returnedValue = repository.save(pFile);
        return returnedValue;
    }



}
