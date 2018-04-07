package com.es.nf.batch;

/**
 *  Main class for Springboot application NF Batch
 * @author
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ApplicationBatch {

    /**
     * Main for Springboot application
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ApplicationBatch.class, args);
    }

}
