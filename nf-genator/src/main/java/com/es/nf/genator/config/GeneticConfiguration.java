
package com.es.nf.genator.config;


import com.es.nf.domain.v1.genetic.entity.HumanGenome;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class GeneticConfiguration {

    @Bean
    public HumanGenome getHumanGenome() {
        return new HumanGenome();
    }


}
