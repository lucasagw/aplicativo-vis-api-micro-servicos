package api.vis.consumo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.vis.consumo.model.ConsumoEnergia;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface ConsumoEnergiaRepository extends JpaRepository<ConsumoEnergia, Long>{

	
	List<ConsumoEnergia> findByEletroUserUsuarioId(Long usuarioId);

	ConsumoEnergia findByEletroUserEletrodomesticoIdAndEletroUserUsuarioId(Long eletroId, Long usuarioId);
}
