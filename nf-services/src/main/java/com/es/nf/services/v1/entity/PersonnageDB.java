package com.es.nf.services.v1.entity;

import com.es.nf.domain.v1.Personnage;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "_id",
        "partyId",
        "prenom",
        "nom",
        "age",
        "email"
})
@Document(collection = "personnages")
public class PersonnageDB extends Personnage {

    @JsonProperty("_id")
    @Id
    private String id;

    public String getId() { return id; }
    public void setId(String id) {
        this.id = id;
    }

}