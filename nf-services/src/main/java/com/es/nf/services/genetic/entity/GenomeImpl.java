package com.es.nf.services.genetic.entity;

import com.es.nf.services.genetic.exception.GeneticException;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

public class GenomeImpl implements Genome {

    private Map<String, GeneInformation> template;

    public GenomeImpl() {
        template = new HashMap<String, GeneInformation>();
    }

    @Override
    public Map<String, GeneInformation> getTemplate() {
        return template;
    }

    @Override
    public void setTemplate(Map<String, GeneInformation> pMap) {
        template= pMap;
    }


    public GeneInformation getGenePosition(String pGeneName) {
        GeneInformation pos = template.get(pGeneName);

        if (pos == null) {
            throw new GeneticException("Cannot find a gene called "
                    + pGeneName
                    + " in genome  " + this.getClass().getName());
        }

        return pos;
    }

}
