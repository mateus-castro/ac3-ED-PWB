package br.com.bandtec.ac3edpweb.models;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Valid
@Entity
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCidade;

    @NotBlank
    private String nomeCidade;

    @NotBlank
    private Double pibCidade;

    @NotBlank
    private Boolean isGrande;

    @NotBlank
    @ManyToOne
    private Pais pais;

    public Cidade(Integer idCidade, String nomeCidade, Double pibCidade, Boolean isGrande, Pais pais) {
        this.idCidade = idCidade;
        this.nomeCidade = nomeCidade;
        this.pibCidade = pibCidade;
        this.isGrande = isGrande;
        this.pais = pais;
    }

    public Cidade(){

    }

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

    @Override
    public String toString() {
        return "Cidade{" +
                "\n\tidCidade: " + idCidade +
                ", \n\tnomeCidade: '" + nomeCidade + '\'' +
                ", \n\tpibCidade: " + pibCidade +
                ", \n\tisGrande: " + isGrande +
                ", \n\tpais: " + pais.toString() +
                "\n}\n";
    }
}
