package com.es.nf.domain.v1;

import com.es.nf.domain.v1.genetic.entity.GeneticCodeImpl;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "_id",
        "partyId",
        "partyIdPere",
        "partyIdMere",
        "geneticCode"
})
public class BiologicalFile {

    @JsonProperty("partyId")
    private Integer partyId;

    @JsonProperty("partyIdPere")
    private Integer partyIdPere;

    @JsonProperty("partyIdMere")
    private Integer partyIdMere;

    @JsonProperty("geneticCode")
    private GeneticCodeImpl geneticCode;

    public Integer getPartyId() { return partyId; }
    public void setPartyId(Integer partyId) { this.partyId = partyId; }

    public GeneticCodeImpl getGeneticCode() { return geneticCode; }
    public void setGeneticCode(GeneticCodeImpl geneticCode) {
        this.geneticCode = geneticCode;
    }

    public Integer getPartyIdPere() { return partyIdPere; }
    public void setPartyIdPere(Integer partyIdPere) { this.partyIdPere = partyIdPere; }

    public Integer getPartyIdMere() { return partyIdMere; }
    public void setPartyIdMere(Integer partyIdMere) { this.partyIdMere = partyIdMere; }

}
