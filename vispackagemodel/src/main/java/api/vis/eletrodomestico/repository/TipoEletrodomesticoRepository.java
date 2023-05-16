package api.vis.eletrodomestico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.vis.eletrodomestico.model.TipoEletrodomestico;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface TipoEletrodomesticoRepository extends JpaRepository<TipoEletrodomestico, Long> {

	
}
