package com.es.nf.services.genetic.entity;

import java.util.Map;

public interface Genome {

    public GenePosition getGenePosition(String pGeneName);
    public Map<String, GenePosition> getTemplate();

}
