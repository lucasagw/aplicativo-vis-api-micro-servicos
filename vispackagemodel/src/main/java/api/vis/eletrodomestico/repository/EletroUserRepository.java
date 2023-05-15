package api.vis.eletrodomestico.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import api.vis.eletrodomestico.model.EletroUser;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface EletroUserRepository extends JpaRepository<EletroUser, Long>{

	
	@Modifying
	@Query(value = "DELETE FROM ELETRO_USER WHERE ELETRODOMESTICO_ID = :eletrodomesticoId" +
			" AND USUARIO_ID = :usuarioId ", nativeQuery = true)
	public void excluirRelacionamentoEletroUser(@Param("eletrodomesticoId") Long eletrodomesticoId,
												@Param("usuarioId") Long usuarioId);
	
	
	@Query(value = "SELECT ELU.ID, ELU.ELETRODOMESTICO_ID, ELU.USUARIO_ID," +
			" ELU.DIA_SEMANA_LIGADO, ELU.TEMPO_DIARIO_LIGADO" +
			" FROM ELETRO_USER ELU" +
            " INNER JOIN ELETRODOMESTICO EL ON EL.ID = ELU.ELETRODOMESTICO_ID" +
            " WHERE ELU.USUARIO_ID = :id", nativeQuery = true)
   public Optional<List<EletroUser>> findByUsuarioId(@Param("id") Long id);


	@Query(value = "SELECT ELU.ID, ELU.ELETRODOMESTICO_ID, ELU.USUARIO_ID," +
			" ELU.DIA_SEMANA_LIGADO, ELU.TEMPO_DIARIO_LIGADO" +
			" FROM ELETRO_USER ELU" +
            " INNER JOIN ELETRODOMESTICO EL ON EL.ID = ELU.ELETRODOMESTICO_ID" +
            " WHERE ELU.USUARIO_ID = :userId" +
            " AND EL.ID = :eletroId", nativeQuery = true)
	public EletroUser findByEletroUsuario(@Param("eletroId") Long eletroId, @Param("userId") Long userId);
}
