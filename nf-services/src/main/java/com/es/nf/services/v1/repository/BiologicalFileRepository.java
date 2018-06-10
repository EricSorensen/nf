package com.es.nf.services.v1.repository;

import com.es.nf.services.v1.entity.BiologicalFileDB;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BiologicalFileRepository extends MongoRepository<BiologicalFileDB, Long> {

    BiologicalFileDB findByPartyId(int partyId);

    List<BiologicalFileDB> findByPartyIdPereInOrPartyIdMereIn(List<Integer> pPeres, List<Integer> pMeres) ;


    BiologicalFileDB insert(BiologicalFileDB file);


}
