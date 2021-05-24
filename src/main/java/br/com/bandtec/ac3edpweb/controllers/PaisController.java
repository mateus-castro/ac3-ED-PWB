package br.com.bandtec.ac3edpweb.controllers;

import br.com.bandtec.ac3edpweb.FilaObjeto;
import br.com.bandtec.ac3edpweb.PilhaObjeto;
import br.com.bandtec.ac3edpweb.models.Operacao;
import br.com.bandtec.ac3edpweb.models.Pais;
import br.com.bandtec.ac3edpweb.models.Requisicao;
import br.com.bandtec.ac3edpweb.repositories.CidadeRepository;
import br.com.bandtec.ac3edpweb.repositories.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/pais")
public class PaisController {
    @Autowired
    private PaisRepository repoPais;

    private PilhaObjeto<Operacao> pilhaOp = new PilhaObjeto<>(10);
    private FilaObjeto<Requisicao> filaReq = new FilaObjeto<>(10);

    @GetMapping
    public ResponseEntity getPais(){
        return ResponseEntity.status(200).body(repoPais.findAll());
    }

    @PostMapping
    public ResponseEntity postPais(@RequestBody Pais novoPais){
        if(!repoPais.existsPaisByNomePais(novoPais.getNomePais())) {
            repoPais.save(novoPais);
            pilhaOp.push(new Operacao("post", novoPais));
            return ResponseEntity.status(201).build();
        } else{
            return ResponseEntity.status(400).body("Pais já registrado!");
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

    @PostMapping("/req")
    public ResponseEntity novaRequisicao() {
        // sorteio de um UUID
        String protocolo = UUID.randomUUID().toString();
        // gerando um momento futuro para daqui a 16 segundos
        LocalDateTime previsao = LocalDateTime.now().plusSeconds(21);
        Thread requisita = new Thread(() -> {
            try {
                Thread.sleep(20_000);
                Integer numero = ThreadLocalRandom.current().nextInt(0, 100);

                Requisicao novaReq = new Requisicao(protocolo, previsao);
                filaReq.insert(novaReq);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        requisita.start();

        return ResponseEntity
                .status(202)
                .header("protocolo", protocolo)
                .header("previsao", previsao.toString())
                .build();
    }

    @GetMapping("/req")
    public ResponseEntity getRequisicao(){
        ArrayList<Requisicao> novaReq = new ArrayList<>(filaReq.getTamanho());
        do{
            novaReq.add(filaReq.poll());
        }
        while(!filaReq.isEmpty());

        return ResponseEntity.status(400).body(novaReq);
    }

//    @GetMapping("/req/{protocolo}")
//    public ResponseEntity getRequisicao(@PathVariable String protocolo){
//        do{
//            Requisicao novaReq = filaReq.poll();
//            if(novaReq.getProtocolo().equals(protocolo)){
//                return ResponseEntity.status(200).body(novaReq);
//            }
//        }
//        while(!filaReq.isEmpty());
//
//        return ResponseEntity.status(400).body("Requisição não foi encontrada.");
//    }

//    public void scheduler(){
//
//    }
}
