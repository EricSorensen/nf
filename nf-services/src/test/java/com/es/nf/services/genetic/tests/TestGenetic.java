package com.es.nf.services.genetic.tests;


import com.es.nf.services.genetic.entity.*;
import junit.framework.JUnit4TestCaseFacade;
import org.junit.Test;
import org.slf4j.Logger;

import static org.junit.Assert.assertTrue;


public class TestGenetic  {

    //private static Logger log = new Logger(TestGenetic.class);

    @Test
    public void checkBit() {

        long value = 24;

        long result = value<<(63-5);
        result= result>>(63-5);
        result = (result >> (3-1));

        System.out.println(result);
    }

    @Test
    public void addGetGene() {
        Chromosome chromovide = new ChromosomeImpl();
        GenePosition position = new GenePosition("HY",3, 4  );

        // Test gene vide
        Gene gene = chromovide.getGene(position);
        System.out.println(gene);

        // ajout d'un gene homozygote
        position.setFirstBit(2);
        position.setLastBit(3);
        gene.setAlleleA(1);
        gene.setAlleleB(1);
        chromovide.addGene(gene, position);
        gene = chromovide.getGene(position);
        assertTrue("Premier gene erreur de récupération",(gene.getAlleleA()==1 && gene.getAlleleB()==1));
        System.out.println(gene);

        // ajout d'un deuxième gene hétérozygote
        position.setFirstBit(5);
        position.setLastBit(6);
        gene.setAlleleA(1);
        gene.setAlleleB(3);
        chromovide.addGene(gene, position);
        gene = chromovide.getGene(position);
        assertTrue("Deuxième gene erreur de récupération",(gene.getAlleleA()==1 && gene.getAlleleB()==3));
        System.out.println(gene);

        // ajout d'un troisième gene hétérozygote
        position.setFirstBit(10);
        position.setLastBit(12);
        gene.setAlleleA(2);
        gene.setAlleleB(5);
        chromovide.addGene(gene, position);
        gene = chromovide.getGene(position);
        assertTrue("Troisième gene erreur de récupération",(gene.getAlleleA()==2 && gene.getAlleleB()==5));
        System.out.println(gene);


        // ajout d'un quatrième gene hétérozygote
        position.setFirstBit(4);
        position.setLastBit(4);
        gene.setAlleleA(1);
        gene.setAlleleB(0);
        chromovide.addGene(gene, position);
        gene = chromovide.getGene(position);
        assertTrue("Quatrième gene erreur de récupération",(gene.getAlleleA()==1 && gene.getAlleleB()==0));
        System.out.println(gene);

        // ajout d'un cinquième gene hétérozygote
        position.setFirstBit(1);
        position.setLastBit(1);
        gene.setAlleleA(0);
        gene.setAlleleB(1);
        chromovide.addGene(gene, position);
        gene = chromovide.getGene(position);
        assertTrue("Cinquième gene erreur de récupération",(gene.getAlleleA()==0 && gene.getAlleleB()==1));
        System.out.println(gene);


        //Re-vérification globale de tous les gènes après avoir injecté les gènes
        // VG d'un gene homozygote
        position.setFirstBit(2);
        position.setLastBit(3);
        gene = chromovide.getGene(position);
        assertTrue("Verif globale premier gene erreur de récupération",(gene.getAlleleA()==1 && gene.getAlleleB()==1));
        System.out.println(gene);

        // VG d'un deuxième gene hétérozygote
        position.setFirstBit(5);
        position.setLastBit(6);
        gene = chromovide.getGene(position);
        assertTrue("Verif globale deuxième gene erreur de récupération",(gene.getAlleleA()==1 && gene.getAlleleB()==3));
        System.out.println(gene);

        // VG d'un troisième gene hétérozygote
        position.setFirstBit(10);
        position.setLastBit(12);
        gene = chromovide.getGene(position);
        assertTrue("Verif globale troisième gene erreur de récupération",(gene.getAlleleA()==2 && gene.getAlleleB()==5));
        System.out.println(gene);


        // VG d'un quatrième gene hétérozygote
        position.setFirstBit(4);
        position.setLastBit(4);
        gene = chromovide.getGene(position);
        assertTrue("Verif globale quatrième gene erreur de récupération",(gene.getAlleleA()==1 && gene.getAlleleB()==0));
        System.out.println(gene);

        // VG d'un cinquième gene hétérozygote
        position.setFirstBit(1);
        position.setLastBit(1);
        gene = chromovide.getGene(position);
        assertTrue("Verif globale cinquième gene erreur de récupération",(gene.getAlleleA()==0 && gene.getAlleleB()==1));
        System.out.println(gene);

    }


    @Test
    public void validateGenome() {

        Genome genome = new HumanGenome();
        GeneticCode codeGen = new GeneticCodeImpl();
        Gene gene = new GeneImpl();

        gene.setName("HAIR_DARKNESS");
        gene.setAlleleA(1);
        gene.setAlleleB(3);

        codeGen.mitose(genome);
        codeGen.addGene(gene, genome);

    }
}
