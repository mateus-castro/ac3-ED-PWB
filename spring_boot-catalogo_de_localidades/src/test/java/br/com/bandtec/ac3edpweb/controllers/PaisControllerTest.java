package br.com.bandtec.ac3edpweb.controllers;

import br.com.bandtec.ac3edpweb.models.Cidade;
import br.com.bandtec.ac3edpweb.models.Operacao;
import br.com.bandtec.ac3edpweb.models.Pais;
import br.com.bandtec.ac3edpweb.repositories.CidadeRepository;
import br.com.bandtec.ac3edpweb.repositories.PaisRepository;
import br.com.bandtec.ac3edpweb.utils.PilhaObjeto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaisControllerTest {
    @Autowired
    PaisController controllerPais;

    @MockBean
    PaisRepository repoPais;

    @Test
    @DisplayName("GET /cidade - Quando o repository tem algum registro")
    void getCidadeRepoComAlgo() {
        List<Pais> listaPais = Arrays.asList(new Pais(), new Pais());
        Mockito.when(repoPais.findAll()).thenReturn(listaPais);
        ResponseEntity res = controllerPais.getPais();
        assertEquals(200, res.getStatusCodeValue());
    }

    @Test
    @DisplayName("GET /pais - Quando o repository não tem nenhum registro")
    void getCidadeRepoSemNada() {
        ResponseEntity res = controllerPais.getPais();
        assertEquals(204, res.getStatusCodeValue());
    }



    @Test
    @DisplayName("POST /pais - Inserindo pais")
    void postCidadeCorreto() {
        Pais pais = new Pais(3, "Noruega");

        ResponseEntity res = controllerPais.postPais(pais);
        assertEquals(201, res.getStatusCodeValue());
    }

    @Test
    @DisplayName("POST /pais - Inserindo cidade com erro")
    void postCidadeErrado() {
        Pais pais = new Pais(4, "Peru");

        ResponseEntity res = controllerPais.postPais(pais);
        assertEquals(400, res.getStatusCodeValue());
    }



    @Test
    @DisplayName("PUT /pais - Quando o pais existe")
    void putCidadeCerto() {
        Mockito.when(repoPais.existsById(new Pais().getIdPais())).thenReturn(true);

        Pais pais = new Pais(4, "Peru");

        ResponseEntity res = controllerPais.putPais(pais);
        assertEquals(201, res.getStatusCodeValue());
    }

    @Test
    @DisplayName("PUT /pais - Quando insere um pais 2")
    void putCidadeComId() {
        Pais pais = new Pais(4, "Peru");

        ResponseEntity res = controllerPais.putPais(pais);
        assertEquals(400, res.getStatusCodeValue());
    }



    @Test
    @DisplayName("DELETE /pais - Deletando um pais")
    void deleteCidadeCerto(){
        Mockito.when(repoPais.existsById(new Pais().getIdPais())).thenReturn(false);
        ResponseEntity res = controllerPais.desfazerPais();
        assertEquals(400, res.getStatusCodeValue());
    }

    @Test
    @DisplayName("DELETE /pais - Pais não existe")
    void deleteCidadeInexistente(){
        Mockito.when(repoPais.existsById(new Pais().getIdPais())).thenReturn(true);
        ResponseEntity res = controllerPais.desfazerPais();
        assertEquals(200, res.getStatusCodeValue());
    }


}