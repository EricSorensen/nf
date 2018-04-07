package com.es.nf.domain.v1.entity;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;



/**
 * Adresse class
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "ville",
        "rue",
        "numero"
})
public class Adresse {

    @JsonProperty("ville")
    private String ville;
    @JsonProperty("rue")
    private String rue;
    @JsonProperty("numero")
    private String numero;

    @JsonProperty("ville")
    public String getVille() {
        return ville;
    }

    @JsonProperty("ville")
    public void setVille(String ville) {
        this.ville = ville;
    }

    @JsonProperty("rue")
    public String getRue() {
        return rue;
    }

    @JsonProperty("rue")
    public void setRue(String rue) {
        this.rue = rue;
    }

    @JsonProperty("numero")
    public String getNumero() {
        return numero;
    }

    @JsonProperty("numero")
    public void setNumero(String numero) {
        this.numero = numero;
    }

}