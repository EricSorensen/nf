package com.es.nf.services.config;

import com.es.nf.domain.v1.genetic.entity.GeneInformation;
import com.es.nf.domain.v1.genetic.entity.Genome;
import com.es.nf.domain.v1.genetic.entity.GenomeImpl;
import com.es.nf.domain.v1.genetic.entity.HumanGenome;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class GeneticConfiguration  {


    @Bean
    public HumanGenome getHumanGenome() {
        return new HumanGenome();
    }

}
