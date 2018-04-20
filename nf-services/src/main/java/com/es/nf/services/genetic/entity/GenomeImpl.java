package com.es.nf.services.genetic.entity;

import com.es.nf.services.genetic.exception.GeneticException;

import java.util.HashMap;
import java.util.Map;

public class GenomeImpl implements Genome {

    private HashMap<String, GenePosition> template;

    protected GenomeImpl() {
        template = new HashMap<String, GenePosition>();
    }

    @Override
    public Map<String, GenePosition> getTemplate() {
        return template;
    }

    public GenePosition getGenePosition(String pGeneName) {
        GenePosition pos = template.get(pGeneName);

        if (pos == null) {
            throw new GeneticException("Cannot find a gene called "
                    + pGeneName
                    + " in genome  " + this.getClass().getName());
        }

        return pos;
    }

}
