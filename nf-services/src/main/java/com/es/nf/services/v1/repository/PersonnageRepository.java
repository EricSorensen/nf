package com.es.nf.services.v1.repository;


import com.es.nf.services.v1.entity.Personnage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface PersonnageRepository extends MongoRepository<Personnage, Long> {

    Personnage findByPartyId(int partyId);

    List<Personnage> findAll();

    //@Query("{personnage: { $regex: ?0 } })")
    //List<Personnage> findCustomByRegExDomain(String domain);

}