package com.es.nf.services.genetic.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.PropertySourcesPlaceholdersResolver;
import org.springframework.util.PropertyPlaceholderHelper;

public class HumanGenome extends GenomeImpl {

    public enum HGene {

        HAIR_DARKNESS ("","",""),
        MCIR("","","");


        private String name;
        private String shortDescription;
        private String longDescription;


        HGene (String pName , String pShortDesc, String pLongDesc) {
            name            = pName;
            shortDescription= pShortDesc;
            longDescription = pLongDesc;

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        public String getShortDescription() {
            return shortDescription;
        }

        public void setShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
        }

        public String getLongDescription() {
            return longDescription;
        }

        public void setLongDescription(String longDescription) {
            this.longDescription = longDescription;
        }

        @Override
        public String toString() {
            return "HGene{" +
                    "name='" + name + '\'' +
                    ", shortDescription='" + shortDescription + '\'' +
                    ", longDescription='" + longDescription + '\'' +
                    '}';
        }
    }

    public HumanGenome() {
        super();

        getTemplate().putIfAbsent("HAIR_DARKNESS", new GenePosition("HY", 1, 2));
        getTemplate().putIfAbsent("MC1R", new GenePosition("HY", 3, 3));

    }

}
