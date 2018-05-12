package com.es.nf.services.v1.entity;

import com.es.nf.services.genetic.entity.GeneticCode;
import com.es.nf.services.genetic.entity.GeneticCodeImpl;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "_id",
        "partyId",
        "geneticCode"
})
@Document(collection = "biologicalfile")
public class BiologicalFile {

    @JsonProperty("_id")
    @Id
    private String id;

    @JsonProperty("partyId")
    private Integer partyId;

    @JsonProperty("geneticCode")
    private GeneticCodeImpl geneticCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPartyId() {
        return partyId;
    }

    public void setPartyId(Integer partyId) {
        this.partyId = partyId;
    }

    public GeneticCodeImpl getGeneticCode() {
        return geneticCode;
    }

    public void setGeneticCode(GeneticCodeImpl geneticCode) {
        this.geneticCode = geneticCode;
    }
}
