package br.com.bandtec.ac3edpweb.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

public class Operacao {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOp;
    private String tipoOp;
    private Pais paisOp;
    private Cidade cidadeOp;

    public Operacao(String tipoOp, Pais paisOp) {
        this.tipoOp = tipoOp;
        this.paisOp = paisOp;
    }

    public Operacao(String tipoOp, Cidade cidadeOp) {
        this.tipoOp = tipoOp;
        this.cidadeOp = cidadeOp;
    }

    public Integer getIdOp() {
        return idOp;
    }

    public void setIdOp(Integer idOp) {
        this.idOp = idOp;
    }

    public String getTipoOp() {
        return tipoOp;
    }

    public void setTipoOp(String tipoOp) {
        this.tipoOp = tipoOp;
    }

    public Pais getPaisOp() {
        return paisOp;
    }

    public void setPaisOp(Pais paisOp) {
        this.paisOp = paisOp;
    }
}
