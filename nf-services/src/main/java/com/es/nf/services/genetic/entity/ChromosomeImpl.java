package com.es.nf.services.genetic.entity;

import sun.java2d.x11.X11SurfaceDataProxy;

import java.util.BitSet;

public class ChromosomeImpl implements Chromosome {

    private String name;

    private long chainA;
    private long chainB;

    public ChromosomeImpl() {
        chainA = 0;
        chainB = 0;
    }

    public Gene getGene(GenePosition pPos){

        int nbBits = pPos.getLastBit() - pPos.getFirstBit() + 1;
        double maskBits = 0;

        if (nbBits == 1) {
            maskBits = 1;
        }else {
            maskBits = Math.pow(2, (nbBits))-1;
        }

        // On décale à droite de manière à ramener les bits
        // recherchés à droite.
        long valueA = chainA >> (pPos.getFirstBit()-1);
        valueA = valueA & (int)maskBits;


        long valueB = chainB >> (pPos.getFirstBit()-1);
        valueB = valueB & (int)maskBits;

        Gene returnValue = new GeneImpl();
        returnValue.setAlleleA((int)valueA);
        returnValue.setAlleleB((int)valueB);

        return returnValue;

    }

    public void addGene(Gene gene, GenePosition pPos) {
        chainA = addAllele(gene.getAlleleA(), pPos, chainA);
        chainB = addAllele(gene.getAlleleB(), pPos, chainB);
    }

    private long addAllele(long valueAllele, GenePosition pPos, long pChromosomeChain) {
        int bitLeft = pPos.getFirstBit();
        long returnValue = 0;

        if (bitLeft>0){
            // On décalage vers la gauche pour placer le
            // gene à la bonne place sur le chromosome
            valueAllele = valueAllele<<(bitLeft-1);

            // On ajoute le gene sur la chaine de chromosome
            returnValue = pChromosomeChain | valueAllele;
        }
        return returnValue;

    }

    @Override
    public String toString() {
        return "ChromosomeImpl{" +
                "chainA=" + Long.toBinaryString(chainA) +
                ", chainB=" + Long.toBinaryString(chainB) +
                '}';
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public void setName(String pName){
        name = pName;
    }

}
