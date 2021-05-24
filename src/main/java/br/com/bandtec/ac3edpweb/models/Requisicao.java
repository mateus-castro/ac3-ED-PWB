package br.com.bandtec.ac3edpweb.models;

import java.time.LocalDateTime;

public class Requisicao {
    private String protocolo;
    private LocalDateTime previsaoPraFinalizar;

    public Requisicao(String protocolo, LocalDateTime previsaoPraFinalizar) {
        this.protocolo = protocolo;
        this.previsaoPraFinalizar = previsaoPraFinalizar;
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
}
