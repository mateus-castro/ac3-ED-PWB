package br.com.bandtec.ac3edpweb.controllers;

import br.com.bandtec.ac3edpweb.PilhaObjeto;
import br.com.bandtec.ac3edpweb.models.Cidade;
import br.com.bandtec.ac3edpweb.models.Operacao;
import br.com.bandtec.ac3edpweb.models.Requisicao;
import br.com.bandtec.ac3edpweb.repositories.CidadeRepository;
import br.com.bandtec.ac3edpweb.repositories.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static br.com.bandtec.ac3edpweb.models.Requisicao.filaReq;

@RestController
@RequestMapping("/cidade")
public class CidadeController {

    @Autowired
    public CidadeRepository repoCidade;

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
            String protocolo = UUID.randomUUID().toString();
            LocalDateTime previsao = LocalDateTime.now().plusSeconds(60);
            filaReq.insert(new Requisicao(protocolo, previsao, novaCidade, repoCidade));
            pilhaOp.push(new Operacao("post", novaCidade));
            return ResponseEntity.status(201)
                    .header("protocolo", protocolo)
                    .header("previsao", previsao.toString())
                    .build();
        } else {
            return ResponseEntity.status(400).body("O país no qual você tentou inserir a cidade, não existe.");
        }

    }

    @PutMapping()
    public ResponseEntity putCidade(@RequestBody Cidade novaCidade){
        if(repoCidade.existsById(novaCidade.getIdCidade())){
            repoCidade.save(novaCidade);
            pilhaOp.push(new Operacao("put", novaCidade));
            return ResponseEntity.status(200).body("Cidade atualizada com sucesso.");
        }
        return ResponseEntity.status(400).body("Cidade não encontrada.");

    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCidade(@PathVariable int id){
        if(repoCidade.existsById(id)){
            Cidade cidade = repoCidade.findById(id).get();
            repoCidade.delete(cidade);

            pilhaOp.push(new Operacao("post", cidade));
            return ResponseEntity.status(200).body("Cidade deletada com sucesso!");
        }
        return ResponseEntity.status(400).body("Cidade não encontrada.");
    }

    @GetMapping("/desfaz")
    public ResponseEntity desfazerCidade(){
        if(pilhaOp.getTopo() >= 0){
            Operacao op = pilhaOp.pop();
            if(op.getTipoOp().equals("post")){
                repoCidade.delete(op.getCidadeOp());
            } else if(op.getTipoOp().equals("delete")){
                repoCidade.save(op.getCidadeOp());
            } else {
                repoCidade.save(op.getCidadeOp());
            }
            return ResponseEntity.status(200).body("Operação desfeita.");
        } else{
            return ResponseEntity.status(400).body("Não há operações para serem desfeitas");
        }
    }
}
