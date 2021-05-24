package br.com.bandtec.ac3edpweb.models;

import javax.persistence.*;

@Entity
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCidade;

    @ManyToOne
    private Pais pais;

    private String nomeCidade;

    private Double pibCidade;

    private Boolean isGrande;

    public Integer getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Integer idCidade) {
        this.idCidade = idCidade;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public Double getPibCidade() {
        return pibCidade;
    }

    public void setPibCidade(Double pibCidade) {
        this.pibCidade = pibCidade;
    }

    public Boolean getGrande() {
        return isGrande;
    }

    public void setGrande(Boolean grande) {
        isGrande = grande;
    }
}
