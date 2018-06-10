package com.es.nf.domain.v1.genetic.entity;

public interface Chromosome {

    public String getName();
    public void setName(String pName);

    public Gene getGene(GeneInformation pPos);
    public void addGene(Gene gene, GeneInformation pPos);

}
