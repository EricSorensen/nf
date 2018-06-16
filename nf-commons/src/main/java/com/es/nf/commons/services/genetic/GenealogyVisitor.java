package com.es.nf.commons.services.genetic;

import com.es.nf.domain.v1.BiologicalFile;

import java.util.List;

public interface GenealogyVisitor {

    /*
     * Method to get the list of colons (i.e personnage that do not have parents)
     *
     * @Return : a list of biological files that are colons
     */
    List<BiologicalFile> getAllBFListPerPartyId();

    void doVisit(BiologicalFile pFile);

}
