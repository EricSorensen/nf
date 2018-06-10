package com.es.nf.genator;


import com.es.nf.genator.processor.AddGeneHairDataProcessor;
import com.es.nf.genator.processor.DataProcessor;
import com.es.nf.genator.processor.GenderDataProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@ComponentScan({"com.es.nf.commons", "com.es.nf.domain", "com.es.nf.genator"})
@SpringBootApplication
public class ApplicationGenator {

    private static final Logger log = LoggerFactory.getLogger(ApplicationGenator.class);

    public static void main(String[] args) {
        SpringApplication.run(ApplicationGenator.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            DataProcessor processor = getProcessor();
            log.info("Démarrage du traitement de mise à jour");
            processor.process();
            log.info("Fin du traitement de mise à jour");
        };
    }

    @Bean
    public DataProcessor getProcessor() {
        //return new GenderDataProcessor();
        return new AddGeneHairDataProcessor();
    }

}