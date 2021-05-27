package br.com.bandtec.ac3edpweb.repositories;

import br.com.bandtec.ac3edpweb.models.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaisRepository extends JpaRepository<Pais, Integer> {
    Boolean existsPaisByNomePais(String nomePais);
}
