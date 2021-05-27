package br.com.bandtec.ac3edpweb.controllers;

import br.com.bandtec.ac3edpweb.models.Requisicao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.bandtec.ac3edpweb.controllers.PaisController.listaReq;

@RestController
@RequestMapping("req")
public class AuxController {

    @GetMapping("/{protocolo}")
    public ResponseEntity getRequisicao(@PathVariable String protocolo){
        for(Requisicao p : listaReq){
            if(p.getProtocolo().equals(protocolo)){
                listaReq.remove(p);
                return ResponseEntity.status(200).body("Requisição POST " + p.getProtocolo()
                        + "concluída, e será deletada desta lista." + "\n" + p.getCidade().toString());
            }
        }

        return ResponseEntity.status(400).body("Protocolo " + protocolo
                + " não encontrado, ou já foi consultado antes.");
    }

}
