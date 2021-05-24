package br.com.bandtec.ac3edpweb.controllers;

import br.com.bandtec.ac3edpweb.PilhaObjeto;
import br.com.bandtec.ac3edpweb.models.Cidade;
import br.com.bandtec.ac3edpweb.models.Operacao;
import br.com.bandtec.ac3edpweb.repositories.CidadeRepository;
import br.com.bandtec.ac3edpweb.repositories.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cidade")
public class CidadeController {

    @Autowired
    private CidadeRepository repoCidade;

    @Autowired
    private PaisRepository repoPais;

    private PilhaObjeto<Operacao> pilhaOp = new PilhaObjeto<>(3);

    @GetMapping
    public ResponseEntity getCidade(){
        return ResponseEntity.status(200).body(repoCidade.findAll());
    }

    @PostMapping
    public ResponseEntity postCidade(@RequestBody Cidade novaCidade){
        if(repoPais.existsById(novaCidade.getPais().getIdPais())){
            repoCidade.save(novaCidade);
            pilhaOp.push(new Operacao("post", novaCidade));
            return ResponseEntity.status(201).build();
        } else {
            return ResponseEntity.status(400).body("País não encontrado.");
        }
    }

    @GetMapping("/desfaz")
    public ResponseEntity desfazerPais(){
        if(pilhaOp.getTopo() >= 0){
            Operacao op = pilhaOp.pop();
            if(op.getTipoOp().equals("post")){
                repoPais.delete(op.getPaisOp());
            } else if(op.getTipoOp().equals("delete")){
                repoPais.save(op.getPaisOp());
            }
            return ResponseEntity.status(200).body("Operação desfeita.");
        } else{
            return ResponseEntity.status(400).body("Não há operações para serem desfeitas");
        }
    }
}
