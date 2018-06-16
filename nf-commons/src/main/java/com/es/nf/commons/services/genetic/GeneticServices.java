package com.es.nf.commons.services.genetic;

import com.es.nf.domain.v1.BiologicalFile;
import com.es.nf.domain.v1.genetic.entity.Genome;

public interface GeneticServices {

     void createChild (BiologicalFile pBFFather,
                             BiologicalFile pBMother,
                             BiologicalFile pChild,
                             Genome         pGenome);

     void visitGenealogy (GenealogyVisitor pVisitor);
}
