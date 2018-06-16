package com.es.nf.services.v1.controller;



import com.es.nf.domain.v1.BiologicalFile;
import com.es.nf.services.v1.entity.BiologicalFileDB;
import com.es.nf.services.v1.repository.BiologicalFileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/biologicalfiles")
    @ResponseBody
    @PreAuthorize("#oauth2.hasScope('biological.read')")
    public Object getBiologicalFile(@RequestParam Map<String, String> customQuery){

        String sortType = customQuery.get("sortType");

        int pageSize = 0;
        if (customQuery.get("pageSize") != null) {
            try {
                pageSize = Integer.valueOf(customQuery.get("pageSize"));
            } catch (NumberFormatException e) {
                throw new RuntimeException("pageSize attribute is invalid. Not a numeric format");
            }

        }

        int pageNumber = 0;
        if (customQuery.get("pageNumber") != null) {
            try {
                pageNumber = Integer.valueOf(customQuery.get("pageNumber"));
            } catch (NumberFormatException e){
                throw new RuntimeException ("pageNumber attribute is invalid. Not a numeric format");
            }
        }

        log.debug("call of /biologicalfile with sortType="+ sortType + " with PageNumber=" +  pageNumber +
                    "And pageSize=" + pageSize);

        List<BiologicalFileDB> list = null;

        Pageable pageable = null;
        if ((pageSize == 0)) {
            //No pageSize means no pagination
            //PageNumber is ignored
            if (sortType != null) {
                pageable = PageRequest.of(0, Integer.MAX_VALUE, Sort.by(Sort.Order.asc(sortType)));
            } else {
                pageable = PageRequest.of(0, Integer.MAX_VALUE);
            }
        } else {
            // pageSize != 0 means pagination
            // Default pageNumber is 0
            if (sortType != null){
                pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Order.asc(sortType)));
                list = repository.findAll(Sort.by(Sort.Order.asc(sortType)));
            } else {
                pageable = PageRequest.of(pageNumber, pageSize);
                list = repository.findAll();
            }
        }

        //Nothing to do except return the value found in repository
        // return the page information
        Page<BiologicalFileDB> page = null;
        page  = repository.findAll(pageable);
        return page;
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
