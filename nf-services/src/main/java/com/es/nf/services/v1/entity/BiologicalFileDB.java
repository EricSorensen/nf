package com.es.nf.services.v1.entity;

import com.es.nf.domain.v1.BiologicalFile;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "biologicalfile")
public class BiologicalFileDB extends BiologicalFile {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("_id")
    @Id
    private String id;

}
