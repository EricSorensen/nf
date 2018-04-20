package com.es.nf.services.genetic.exception;

public class GeneticException extends RuntimeException {

    public GeneticException(String pDesc) {
        super(pDesc, null);
    }

    public GeneticException (String pDesc, Throwable t) {
        super(pDesc, t);
    }

}
