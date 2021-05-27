package br.com.bandtec.ac3edpweb.repositories;

import br.com.bandtec.ac3edpweb.models.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
