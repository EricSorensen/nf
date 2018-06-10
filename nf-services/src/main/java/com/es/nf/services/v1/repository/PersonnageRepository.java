package com.es.nf.services.v1.repository;


import com.es.nf.services.v1.entity.PersonnageDB;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface PersonnageRepository extends MongoRepository<PersonnageDB, Long> {

    PersonnageDB findByPartyId(int partyId);

    List<PersonnageDB> findAll();

    //@Query("{personnage: { $regex: ?0 } })")
    //List<Personnage> findCustomByRegExDomain(String domain);

}