package com.es.nf.services.v1.entity;

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
        "adresse",
        "age",
        "email"
})
@Document(collection = "personnages")
public class Personnage {

    @JsonProperty("_id")
    @Id
    private String id;

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