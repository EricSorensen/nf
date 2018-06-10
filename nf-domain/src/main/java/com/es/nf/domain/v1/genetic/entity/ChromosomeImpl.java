package com.es.nf.domain.v1.genetic.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "code"
})
public class ChromosomeImpl implements Chromosome {

    @JsonProperty("name")
    private String name;

    @JsonProperty("chainA")
    private long chainA;

    @JsonProperty("chainB")
    private long chainB;

    public ChromosomeImpl() {
        chainA = 0;
        chainB = 0;
    }

    public Gene getGene(GeneInformation pPos){

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

    public void addGene(Gene gene, GeneInformation pPos) {
        chainA = addAllele(gene.getAlleleA(), pPos, chainA);
        chainB = addAllele(gene.getAlleleB(), pPos, chainB);
    }

    private long addAllele(long valueAllele, GeneInformation pPos, long pChromosomeChain) {
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

    public long getChainA() {
        return chainA;
    }

    public void setChainA(long chainA) {
        this.chainA = chainA;
    }

    public long getChainB() {
        return chainB;
    }

    public void setChainB(long chainB) {
        this.chainB = chainB;
    }

}
