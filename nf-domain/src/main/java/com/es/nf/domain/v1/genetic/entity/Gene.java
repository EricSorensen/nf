package com.es.nf.domain.v1.genetic.entity;

public interface Gene {

    public int getAlleleA();
    public int getAlleleB();

    public void setAlleleA(int pA);
    public void setAlleleB(int pB);

    public String getName();
    public void setName(String pName);


}
