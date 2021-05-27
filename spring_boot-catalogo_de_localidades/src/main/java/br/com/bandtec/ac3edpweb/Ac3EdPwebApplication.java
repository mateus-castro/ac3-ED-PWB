package br.com.bandtec.ac3edpweb;

import br.com.bandtec.ac3edpweb.models.Requisicao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class Ac3EdPwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ac3EdPwebApplication.class, args);
	}
}
