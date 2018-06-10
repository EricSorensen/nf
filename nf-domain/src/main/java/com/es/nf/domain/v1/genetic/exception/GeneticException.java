package com.es.nf.domain.v1.genetic.exception;

public class GeneticException extends RuntimeException {

    public GeneticException(String pDesc) {
        super(pDesc, null);
    }

    public GeneticException (String pDesc, Throwable t) {
        super(pDesc, t);
    }

}
