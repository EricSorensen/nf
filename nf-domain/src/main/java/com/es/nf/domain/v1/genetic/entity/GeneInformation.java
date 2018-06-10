package com.es.nf.domain.v1.genetic.entity;


import com.es.nf.domain.v1.genetic.exception.GeneticException;

public class GeneInformation {

    public GeneInformation(){

    }

    public GeneInformation(String   name,
                           String   shortDesc,
                           String   longDesc,
                           String   chromosomeName,
                           int      firstBit,
                           int      lastBit) {
        setGeneName(name);
        setShortDesc(shortDesc);
        setLongDesc(longDesc);
        setChromosomeName(chromosomeName);
        setFirstBit(firstBit);
        setLastBit(lastBit);
    }

    private String geneName;

    private String shortDesc;

    private String longDesc;


    /**
     * Name of the Chrosomome vers we want to find the gene
     */
    private String chromosomeName;

    /**
     * Position of first byte used to code the gene
     */
    private int firstBit;

    /**
     * Position of last byte used to code the gene
     */
    private int lastBit;


    public int getLastBit() {
        return lastBit;
    }

    public void setLastBit(int pLastLast) {
        if (pLastLast<1 || pLastLast>64) {
            throw new GeneticException("Cannot set a last position not between 1 and 64 :" + Integer.toString(pLastLast));
        }
        this.lastBit = pLastLast;
    }

    public int getFirstBit() {
        return firstBit;
    }

    public void setFirstBit(int pFirstbit) throws GeneticException {
        if (pFirstbit<1 || pFirstbit>64) {
            throw new GeneticException("Cannot set a first position not between 1 and 64 :" + Integer.toString(pFirstbit));
        }
        this.firstBit = pFirstbit;
    }

    public String getChromosomeName() {
        return chromosomeName;
    }

    public void setChromosomeName(String chromosomeName) {
        this.chromosomeName = chromosomeName;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getGeneName() {
        return geneName;
    }

    public void setGeneName(String geneName) {
        this.geneName = geneName;
    }
}
