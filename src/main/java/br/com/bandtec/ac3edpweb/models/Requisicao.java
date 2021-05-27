package br.com.bandtec.ac3edpweb.models;

import br.com.bandtec.ac3edpweb.FilaObjeto;
import br.com.bandtec.ac3edpweb.repositories.CidadeRepository;
import br.com.bandtec.ac3edpweb.repositories.PaisRepository;

import java.time.LocalDateTime;

public class Requisicao {
    private String protocolo;
    private LocalDateTime previsaoPraFinalizar;
    private Pais pais;
    private PaisRepository repoPais;
    private Cidade cidade;
    private CidadeRepository repoCidade;

    public static FilaObjeto<Requisicao> filaReq = new FilaObjeto<>(10);

    public Requisicao(String protocolo, LocalDateTime previsaoPraFinalizar, Pais pais, PaisRepository repo) {
        this.protocolo = protocolo;
        this.previsaoPraFinalizar = previsaoPraFinalizar;
        this.pais = pais;
        this.repoPais = repo;
    }

    public Requisicao(String protocolo, LocalDateTime previsaoPraFinalizar, Cidade cidade, CidadeRepository repo) {
        this.protocolo = protocolo;
        this.previsaoPraFinalizar = previsaoPraFinalizar;
        this.cidade = cidade;
        this.repoCidade = repo;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public LocalDateTime getPrevisaoPraFinalizar() {
        return previsaoPraFinalizar;
    }

    public void setPrevisaoPraFinalizar(LocalDateTime previsaoPraFinalizar) {
        this.previsaoPraFinalizar = previsaoPraFinalizar;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public PaisRepository getRepoPais() {
        return repoPais;
    }

    public void setRepoPais(PaisRepository repoPais) {
        this.repoPais = repoPais;
    }

    public CidadeRepository getRepoCidade() {
        return repoCidade;
    }

    public void setRepoCidade(CidadeRepository repoCidade) {
        this.repoCidade = repoCidade;
    }

}
