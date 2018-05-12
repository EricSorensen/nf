package com.es.nf.services.config;

import com.es.nf.services.genetic.entity.GeneInformation;
import com.es.nf.services.genetic.entity.Genome;
import com.es.nf.services.genetic.entity.GenomeImpl;

import com.es.nf.services.genetic.exception.GeneticException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
@ConfigurationProperties(prefix = "genetic")
public class GeneticConfiguration  {

    private List<String> humangenomeref = new ArrayList<>();

    private Genome hg;

    public List<String> getHumangenomeref() {
        return humangenomeref;
    }

    @Bean
    public Genome getHumanGenome() {
        hg= new GenomeImpl();

        init();
        return hg;
    }

    public void init() {
        Map<String, GeneInformation> mp = new HashMap<String,GeneInformation>();

        for (String gene : humangenomeref) {
            GeneInformation gi = new GeneInformation();

            String[] genevalue  = gene.split(";");

            gi.setGeneName(genevalue[0]);
            gi.setShortDesc(genevalue[1]);
            gi.setLongDesc(genevalue[2]);
            gi.setChromosomeName(genevalue[3]);
            gi.setFirstBit(Integer.valueOf(genevalue[4]).intValue());
            gi.setLastBit(Integer.valueOf(genevalue[5]).intValue());

            hg.getTemplate().putIfAbsent(gi.getGeneName(), gi);
        }

    }

}
