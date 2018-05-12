package com.es.nf.services.genetic.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;


public class HumanGenome extends GenomeImpl {


    private HashMap<String, GeneInformation> map;

    public HumanGenome() {
        super();
    }

    public void init() {
        setTemplate(map);
    }


}
