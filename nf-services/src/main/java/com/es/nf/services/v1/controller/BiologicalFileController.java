package com.es.nf.services.v1.controller;



import com.es.nf.services.v1.entity.BiologicalFile;
import com.es.nf.services.v1.repository.BiologicalFileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
        return repository.findByPartyId(partyId);
    }

    @PostMapping("/biologicalfiles")
    @ResponseBody
    @PreAuthorize("#oauth2.hasScope('biological.write')")
    public BiologicalFile createBiologicalFile(@RequestBody BiologicalFile pFile){

        log.debug("call of POST /biologicalfile/");

        //Create the Biological file
        return repository.insert(pFile);
    }

}
