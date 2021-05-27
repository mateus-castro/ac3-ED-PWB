package br.com.bandtec.ac3edpweb.utils;

import br.com.bandtec.ac3edpweb.models.Requisicao;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static br.com.bandtec.ac3edpweb.models.Requisicao.filaReq;
import static br.com.bandtec.ac3edpweb.controllers.PaisController.listaReq;

@Component
public class Agendamento {

    @Scheduled(cron = "*/30 * * * * *")
    public void scheduleAtv(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String now = LocalDateTime.now().format(format);
        if (!filaReq.isEmpty()) {
            Requisicao req = filaReq.poll();
            listaReq.add(req);
            if(req.getRepoPais() == null){
                req.getRepoCidade().save(req.getCidade());
            } else {
                req.getRepoPais().save(req.getPais());
            }

            System.out.println("Requisição " + req.getProtocolo() + " tratada - " + now);
        } else{
            System.out.println("Nenhum registro encontrado - " + now);
        }
    }

}
