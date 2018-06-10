package com.es.nf.domain.v1;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "_id",
        "partyId",
        "prenom",
        "nom",
        "age",
        "email"
})
public class Personnage {
    @JsonProperty("partyId")
    private Integer partyId;

    @JsonProperty("prenom")
    private String prenom;

    @JsonProperty("nom")
    private String nom;

    @JsonProperty("age")
    private Integer age;

    @JsonProperty("email")
    private String email;


    public Integer getPartyId() {
        return partyId;
    }
    public void setPartyId(Integer partyId) {
        this.partyId = partyId;
    }

    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}