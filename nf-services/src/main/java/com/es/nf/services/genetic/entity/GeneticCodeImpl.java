package com.es.nf.services.genetic.entity;

import com.es.nf.services.genetic.exception.GeneticException;

import java.util.HashMap;
import java.util.Map;

public class GeneticCodeImpl implements GeneticCode{

    private Map<String, Chromosome> code;

    public GeneticCodeImpl(){
        code = new HashMap<String, Chromosome>();
    }

    @Override
    public Gene getGene(String pGeneName, Genome pGenome) {
        // Try to find the gene in the Genome
        GeneInformation pos = pGenome.getGenePosition(pGeneName);

        // If we are here, it means that we found the gene in the genome
        // now we find the right chromosome
        Chromosome chromosome = getChromosome(pGeneName, pos);


        // Here we found a Chromosome. Now we just need to find the gene in the chromosome
        Gene returnValue = chromosome.getGene(pos);
        returnValue.setName(pGeneName);

        // Here we found the gene
        return returnValue;

    }

    private Chromosome getChromosome(String pChromosomeName) {
        Chromosome returnedValue = code.get(pChromosomeName);

        if (returnedValue == null ) {
            returnedValue = new ChromosomeImpl();
            returnedValue.setName(pChromosomeName);
            code.put(pChromosomeName, returnedValue);
        }

        return returnedValue;
    }

    public void addGene(Gene pGene, Genome pGenome) throws GeneticException {
        // Try to find the gene in the Genome
        GeneInformation pos = pGenome.getGenePosition(pGene.getName());

        //Check if the value for the gene can be stored in this position
        int nbBits = pos.getLastBit() - pos.getFirstBit() + 1;
        double maxValue = Math.pow(2, (nbBits)) - 1;

        if ((pGene.getAlleleA()>maxValue) || (pGene.getAlleleB()>maxValue)) {
            int wrongValue;
            if (pGene.getAlleleA() > pGene.getAlleleB()) {
                wrongValue = pGene.getAlleleA();
            } else {
                wrongValue = pGene.getAlleleB();
            }
            throw new GeneticException("Gene " + pGene.getName() + ":"
                                        + "Value " + Integer.toString(wrongValue)
                                        + " too long for a "
                                        + Integer.toString(nbBits) + " gene length");
        }


        // If we are here, it means that we found the gene in the genome
        // now we find the right chromosome
        Chromosome chromosome = getChromosome(pGene.getName(), pos);

        chromosome.addGene(pGene, pos);
    }

    private Chromosome getChromosome(String pGeneName, GeneInformation pPos) {


        Chromosome chromosome = getChromosome(pPos.getChromosomeName());
        if (pPos == null) {
            throw new GeneticException("Cannot find a chromosome called "
                    + pPos.getChromosomeName()
                    + " to locate gene called"
                    + pGeneName
                    + " in genome  " + this.getClass().getName());
        }

        return chromosome;
    }

    public void mitose(Genome pGenome) {

        for (GeneInformation pos : pGenome.getTemplate().values()) {

            if (code.get(pos.getChromosomeName())== null){
                Chromosome chromosome = new ChromosomeImpl();
                chromosome.setName(pos.getChromosomeName());
                code.putIfAbsent(pos.getChromosomeName(), chromosome);
            }

        }
    }


}

