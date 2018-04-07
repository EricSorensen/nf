package com.es.nf.domain.v1.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Personnage class connected to NF Mongo DB Personnage Collection
 *
 * @author : Eric Lewanwdowski
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "_id",
        "partyId",
        "prenom",
        "nom",
        "adresse",
        "age",
        "email"
})
public class Personnage {

    @JsonProperty("_id")
    private String id;
    @JsonProperty("partyId")
    private Integer partyId;
    @JsonProperty("prenom")
    private String prenom;
    @JsonProperty("nom")
    private String nom;
    @JsonProperty("adresse")
    private Adresse adresse;
    @JsonProperty("age")
    private Integer age;
    @JsonProperty("email")
    private String email;


    @JsonProperty("_id")
    public String getId() {
        return id;
    }

    @JsonProperty("_id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("partyId")
    public Integer getPartyId() {
        return partyId;
    }

    @JsonProperty("partyId")
    public void setPartyId(Integer partyId) {
        this.partyId = partyId;
    }

    @JsonProperty("prenom")
    public String getPrenom() {
        return prenom;
    }

    @JsonProperty("prenom")
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @JsonProperty("nom")
    public String getNom() {
        return nom;
    }

    @JsonProperty("nom")
    public void setNom(String nom) {
        this.nom = nom;
    }

    @JsonProperty("adresse")
    public Adresse getAdresse() {
        return adresse;
    }

    @JsonProperty("adresse")
    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    @JsonProperty("age")
    public Integer getAge() {
        return age;
    }

    @JsonProperty("age")
    public void setAge(Integer age) {
        this.age = age;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

}