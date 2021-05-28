package br.com.bandtec.ac3edpweb.controllers;

import br.com.bandtec.ac3edpweb.models.Pais;
import br.com.bandtec.ac3edpweb.models.Requisicao;
import br.com.bandtec.ac3edpweb.repositories.CidadeRepository;
import br.com.bandtec.ac3edpweb.repositories.PaisRepository;
import br.com.bandtec.ac3edpweb.utils.ArquivoHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuxControllerTest {

    @Autowired
    AuxController controllerAux;

    @Autowired
    PaisController controllerPais;

    LocalDateTime now = LocalDateTime.now();

    @MockBean
    PaisRepository repoPais;

    @MockBean
    CidadeRepository repoCidade;

    @Test
    @DisplayName("GET /req - Quando houver protocolo na lista")
    void getRequisicaoRetorno200() {
        controllerPais.listaReq.add(new Requisicao("92fb2709-e2ba-4d1d-bf61-b1b2c9462101", now, new Pais(4, "Honduras"), repoPais));

        ResponseEntity res = controllerAux.getRequisicao("92fb2709-e2ba-4d1d-bf61-b1b2c9462101");

        assertEquals(200, res.getStatusCodeValue());
        assertEquals("Requisição POST concluída, e será deletada desta lista.", res.getBody());
    }

    @Test
    @DisplayName("GET /req - Quando NÃO houver protocolo na lista")
    void getRequisicaoRetorno400() {
        AuxController controllerAux = new AuxController();

        ResponseEntity res = controllerAux.getRequisicao("0000");

        assertEquals(400, res.getStatusCodeValue());
        assertEquals("Protocolo não encontrado, ou já foi consultado antes.", res.getBody());
    }



    @Test
    @DisplayName("POST /req/arquivo - Quando o arquivo txt estiver certo")
    void postArquivoCerto(){
        ResponseEntity res = controllerAux.postArquivo("teste.txt");
        controllerPais.postPais(new Pais(1, "Brasil"));
        controllerPais.postPais(new Pais(2, "Mexico"));
//        ArquivoHandler arquivoHandler = new ArquivoHandler();
        assertEquals(201, res.getStatusCodeValue());
        assertEquals("Arquivo foi processado :D" , res.getBody());
    }

    @Test
    @DisplayName("POST /req/arquivo - Quando o arquivo txt NÃO estiver certo")
    void postArquivoQuebrado(){
        ResponseEntity res = controllerAux.postArquivo("ladygaga");
        assertEquals(400, res.getStatusCodeValue());
        assertEquals("Houve um erro ao processar o arquivo." , res.getBody());
    }
}