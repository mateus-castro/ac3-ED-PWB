package br.com.bandtec.ac3edpweb.controllers;

import br.com.bandtec.ac3edpweb.models.Requisicao;
import br.com.bandtec.ac3edpweb.repositories.CidadeRepository;
import br.com.bandtec.ac3edpweb.repositories.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.bandtec.ac3edpweb.controllers.PaisController.listaReq;
import static br.com.bandtec.ac3edpweb.utils.ArquivoHandler.leArquivo;

@RestController
@RequestMapping("req")
public class AuxController {

    @Autowired
    private CidadeRepository repoCidade;

    @Autowired
    private PaisRepository repoPais;

    @GetMapping("/{protocolo}")
    public ResponseEntity getRequisicao(@PathVariable String protocolo){
        for(Requisicao p : listaReq){
            if(p.getProtocolo().equals(protocolo)){
                listaReq.remove(p);
                return ResponseEntity.status(200).body("Requisição POST concluída, e será deletada desta lista.");
            }
        }

        return ResponseEntity.status(400).body("Protocolo não encontrado, ou já foi consultado antes.");
    }

    @PostMapping("/arquivo/{nome}")
    public ResponseEntity postArquivo(@PathVariable String nome){
        try{
            leArquivo(nome, repoCidade, repoPais);
            return ResponseEntity.status(201).body("Arquivo foi processado :D");
        } catch(Exception e){
            System.out.println(e);
            return ResponseEntity.status(400).body("Houve um erro ao processar o arquivo.");
        }

    }
}
