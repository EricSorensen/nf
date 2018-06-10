package com.es.nf.domain.v1.genetic.entity;

import java.util.Map;

public interface Genome {


    public GeneInformation getGenePosition(String pGeneName);

    public Map<String, GeneInformation> getTemplate();
    void setTemplate(Map<String, GeneInformation> pMap);

}
