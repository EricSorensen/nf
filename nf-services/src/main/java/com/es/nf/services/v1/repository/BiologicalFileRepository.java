package com.es.nf.services.v1.repository;

import com.es.nf.services.v1.entity.BiologicalFile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BiologicalFileRepository extends MongoRepository<BiologicalFile, Long> {

    BiologicalFile findByPartyId(int partyId);

    BiologicalFile insert(BiologicalFile file);

}
