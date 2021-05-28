package br.com.bandtec.ac3edpweb.controllers;

import br.com.bandtec.ac3edpweb.utils.PilhaObjeto;
import br.com.bandtec.ac3edpweb.models.Operacao;
import br.com.bandtec.ac3edpweb.models.Pais;
import br.com.bandtec.ac3edpweb.models.Requisicao;
import br.com.bandtec.ac3edpweb.repositories.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static br.com.bandtec.ac3edpweb.models.Requisicao.filaReq;

@RestController
@RequestMapping("/pais")
public class PaisController {

    @Autowired
    public PaisRepository repoPais;

    private PilhaObjeto<Operacao> pilhaOp = new PilhaObjeto<>(10);
    public static List<Requisicao> listaReq = new ArrayList<>(10);

    @GetMapping
    public ResponseEntity getPais(){
        List lista = new ArrayList<>(repoPais.findAll());
        if (lista.isEmpty()){
            return ResponseEntity.status(204).build();
        }return ResponseEntity.status(200).body(repoPais.findAll());
    }

    @PostMapping
    public ResponseEntity postPais(@RequestBody Pais novoPais){
        if(!repoPais.existsPaisByNomePais(novoPais.getNomePais())) {
            if(novoPais.getIdPais() == null) {
                String protocolo = UUID.randomUUID().toString();
                LocalDateTime previsao = LocalDateTime.now().plusSeconds(60);
                filaReq.insert(new Requisicao(protocolo, previsao, novoPais, repoPais));
                pilhaOp.push(new Operacao("post", novoPais));
                return ResponseEntity.status(201)
                        .header("protocolo", protocolo)
                        .header("previsao", previsao.toString())
                        .build();
            }
            return ResponseEntity.status(400).body("Pode deixar, não precisa colocar o ID na mão." +
                    " Tire-o pra poder inserir no banco :D");
        } else{
            return ResponseEntity.status(400).body("Pais já registrado!");
        }
    }

    @PutMapping
    public ResponseEntity putPais(@RequestBody Pais novoPais){
        if(repoPais.existsById(novoPais.getIdPais())){
            repoPais.save(novoPais);
            pilhaOp.push(new Operacao("put", novoPais));
            return ResponseEntity.status(200).body("País atualizado com sucesso.");
        }
        return ResponseEntity.status(400).body("País não encontrado.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePais(@PathVariable int id){
        if(repoPais.existsById(id)){
            Pais novoPais = repoPais.findById(id).get();
            repoPais.delete(novoPais);

            pilhaOp.push(new Operacao("delete", novoPais));
            return ResponseEntity.status(200).body("País deletado com sucesso!");
        }
        return ResponseEntity.status(400).body("País não encontrado.");
    }

}
