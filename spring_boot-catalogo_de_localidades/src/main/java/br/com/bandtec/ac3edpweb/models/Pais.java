package br.com.bandtec.ac3edpweb.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Valid
@Entity
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPais;

    @NotBlank
    private String nomePais;

    public Pais(Integer idPais, String nomePais) {
        this.idPais = idPais;
        this.nomePais = nomePais;
    }

    public Pais(){

    }

    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    public String getNomePais() {
        return nomePais;
    }

    public void setNomePais(String nomePais) {
        this.nomePais = nomePais;
    }

    @Override
    public String toString() {
        return "Pais{" +
                "\n\tidPais: " + idPais +
                ", \n\tnomePais: '" + nomePais + '\'' +
                "\n}";
    }
}
