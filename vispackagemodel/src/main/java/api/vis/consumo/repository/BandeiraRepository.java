package api.vis.consumo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import api.vis.consumo.model.Bandeira;

@Repository
@Transactional
public interface BandeiraRepository extends JpaRepository<Bandeira, Long> {

	
	Bandeira findByflagType(byte flagType);

}
