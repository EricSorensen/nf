package com.es.nf.domain.v1.genetic.entity;

public class GeneImpl implements Gene {

    private String name;
    private int alleleA;
    private int alleleB;

    @Override
    public int getAlleleA() {
        return alleleA;
    }

    @Override
    public int getAlleleB() {
        return alleleB;
    }

    public void setAlleleA(int pA){
        alleleA = pA;
    }

    public void setAlleleB(int pB){
        alleleB = pB;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String pName) {
        name = pName;
    }

    @Override
    public String toString() {
        return "GeneImpl{" +
                "alleleA=" + alleleA +
                ", alleleB=" + alleleB +
                '}';
    }
}
