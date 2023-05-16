package api.vis.consumo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import api.vis.consumo.model.BandeiraTarifaria;

@Repository
@Transactional
public interface BandeiraTarifariaRepository extends JpaRepository<BandeiraTarifaria, Long> {

	
	@Query(value = "SELECT * FROM BANDEIRA_TARIFARIA WHERE EXTRACT(MONTH FROM DATA) = :month", nativeQuery = true)
	BandeiraTarifaria findByMonth(@Param("month") Integer month);

}
