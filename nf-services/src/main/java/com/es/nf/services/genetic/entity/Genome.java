package com.es.nf.services.genetic.entity;

import java.util.Map;

public interface Genome {


    public GeneInformation getGenePosition(String pGeneName);

    public Map<String, GeneInformation> getTemplate();
    void setTemplate(Map<String, GeneInformation> pMap);

}
