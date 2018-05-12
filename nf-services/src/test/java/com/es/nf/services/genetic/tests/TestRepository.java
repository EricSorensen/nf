package com.es.nf.services.genetic.tests;

import com.es.nf.services.config.GeneticConfiguration;
import com.es.nf.services.config.SpringMongoConfig;
import com.es.nf.services.genetic.entity.*;
import com.es.nf.services.v1.entity.BiologicalFile;
import com.es.nf.services.v1.repository.BiologicalFileRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@DataMongoTest
@Import({GeneticConfiguration.class, SpringMongoConfig.class})
public class TestRepository {

    @Autowired
    BiologicalFileRepository repo;

    @Autowired
    Genome genome;

    @Test
    public void testBiologicalFile() {

        BiologicalFile bFile = new BiologicalFile();
        GeneticCodeImpl genCode= new GeneticCodeImpl();
        //Genome genome = new HumanGenome();

        Gene gene = new GeneImpl();
        gene.setName("H-BB");
        gene.setAlleleA(1);
        gene.setAlleleB(3);
        genCode.addGene(gene, genome);

        Gene gene2 = new GeneImpl();
        gene2.setName("H-R");
        gene2.setAlleleA(1);
        gene2.setAlleleB(0);
        genCode.addGene(gene2, genome);

        bFile.setPartyId(1);
        bFile.setGeneticCode(genCode);
        repo.insert(bFile);
    }

}
