package br.com.bandtec.ac3edpweb.controllers;

import br.com.bandtec.ac3edpweb.models.Cidade;
import br.com.bandtec.ac3edpweb.models.Operacao;
import br.com.bandtec.ac3edpweb.models.Pais;
import br.com.bandtec.ac3edpweb.repositories.CidadeRepository;
import br.com.bandtec.ac3edpweb.utils.PilhaObjeto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CidadeControllerTest {

    @Autowired
    CidadeController controllerCidade;

    @MockBean
    CidadeRepository repoCidade;

    @Test
    @DisplayName("GET /cidade - Quando o repository tem algum registro")
    void getCidadeRepoComAlgo() {
        List<Cidade> listaCidade = Arrays.asList(new Cidade(), new Cidade());
        Mockito.when(repoCidade.findAll()).thenReturn(listaCidade);
        ResponseEntity res = controllerCidade.getCidade();
        assertEquals(200, res.getStatusCodeValue());
    }

    @Test
    @DisplayName("GET /cidade - Quando o repository não tem nenhum registro")
    void getCidadeRepoSemNada() {
        ResponseEntity res = controllerCidade.getCidade();
        assertEquals(204, res.getStatusCodeValue());
    }



    @Test
    @DisplayName("POST /cidade - Inserindo cidade com pais 3")
    void postCidadeCorreto() {
        Pais pais = new Pais(3, "Noruega");
        Cidade cidade = new Cidade();
        cidade.setNomeCidade("Oslo");
        cidade.setPibCidade(8.9);
        cidade.setGrande(true);
        cidade.setPais(pais);

        ResponseEntity res = controllerCidade.postCidade(cidade);
        assertEquals(201, res.getStatusCodeValue());
    }

    @Test
    @DisplayName("POST /cidade - Inserindo cidade com erro")
    void postCidadeErrado() {
        Pais pais = new Pais(4, "Peru");
        Cidade cidade = new Cidade();
        cidade.setNomeCidade("Alto");
        cidade.setPibCidade(6.2);
        cidade.setGrande(false);
        cidade.setPais(pais);

        ResponseEntity res = controllerCidade.postCidade(cidade);
        assertEquals(400, res.getStatusCodeValue());
    }



    @Test
    @DisplayName("PUT /cidade - Quando a cidade existe")
    void putCidadeCerto() {
        Mockito.when(repoCidade.existsById(new Cidade().getIdCidade())).thenReturn(true);

        Pais pais = new Pais(4, "Peru");
        Cidade cidade = new Cidade();
        cidade.setNomeCidade("Alto");
        cidade.setPibCidade(6.2);
        cidade.setGrande(false);
        cidade.setPais(pais);

        ResponseEntity res = controllerCidade.putCidade(cidade);
        assertEquals(201, res.getStatusCodeValue());
    }

    @Test
    @DisplayName("PUT /cidade - Quando insere um id no body")
    void putCidadeComId() {
        Pais pais = new Pais(4, "Peru");
        Cidade cidade = new Cidade();
        cidade.setIdCidade(2);
        cidade.setNomeCidade("Alto");
        cidade.setPibCidade(6.2);
        cidade.setGrande(false);
        cidade.setPais(pais);

        ResponseEntity res = controllerCidade.putCidade(cidade);
        assertEquals(400, res.getStatusCodeValue());
    }



    @Test
    @DisplayName("DELETE /cidade - Deletando uma cidade")
    void deleteCidadeCerto(){
        Mockito.when(repoCidade.existsById(new Cidade().getIdCidade())).thenReturn(false);
        ResponseEntity res = controllerCidade.desfazerCidade();
        assertEquals(400, res.getStatusCodeValue());
    }

    @Test
    @DisplayName("DELETE /cidade - Cidade não existe")
    void deleteCidadeInexistente(){
        Mockito.when(repoCidade.existsById(new Cidade().getIdCidade())).thenReturn(true);
        ResponseEntity res = controllerCidade.desfazerCidade();
        assertEquals(200, res.getStatusCodeValue());
    }



    @Test
    @DisplayName("GET /cidade/desfaz - Desfazendo POST")
    void conseguindoDesfazerPostCidade() {
        PilhaObjeto<Operacao> pilha = new PilhaObjeto<>(10);
        Operacao op = new Operacao("post", new Cidade());
        ResponseEntity res = controllerCidade.desfazerCidade();
        assertEquals(400, res.getStatusCodeValue());
    }

    @Test
    @DisplayName("GET /cidade/desfaz - Desfazendo DELETE")
    void conseguindoDesfazerPutCidade() {
        PilhaObjeto<Operacao> pilha = new PilhaObjeto<>(10);
        Operacao op = new Operacao("delete", new Cidade());
        ResponseEntity res = controllerCidade.desfazerCidade();
        assertEquals(200, res.getStatusCodeValue());
    }
}