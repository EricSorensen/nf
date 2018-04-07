package com.es.nf.services.v1.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.mongodb.core.mapping.Document;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "ville",
        "rue",
        "numero"
})

@Document(collection = "personnages")
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