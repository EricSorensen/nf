package com.es.nf.services.genetic.entity;

import com.es.nf.services.genetic.exception.GeneticException;

public interface GeneticCode {

    public Gene getGene(String pName, Genome pGenome);
    public void addGene(Gene pGene, Genome pGenome) throws GeneticException;

    public void mitose(Genome pGenome);
}
