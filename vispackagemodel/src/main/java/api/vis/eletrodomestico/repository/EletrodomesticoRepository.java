package api.vis.eletrodomestico.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import api.vis.eletrodomestico.model.Eletrodomestico;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface EletrodomesticoRepository extends JpaRepository<Eletrodomestico, Long> {

	
	@Query(value = "SELECT EL.ID, EL.NOME, EL.TIPO_ELETRODOMESTICO_ID, EL.MARCA, EL.MODELO, EL.VOLTAGEM, EL.POTENCIA," +
			" ELU.DIA_SEMANA_LIGADO, ELU.TEMPO_DIARIO_LIGADO" +
			" FROM ELETRODOMESTICO EL" +
            " INNER JOIN ELETRO_USER ELU ON ELU.ELETRODOMESTICO_ID = EL.ID" +
            " WHERE ELU.USUARIO_ID = :id", nativeQuery = true)
   public Optional<List<Eletrodomestico>> findByUsuarioId(@Param("id") Long id);
	
	
	@Query(value = "SELECT EL.ID, EL.NOME, EL.TIPO_ELETRODOMESTICO_ID, EL.MARCA, EL.MODELO, EL.VOLTAGEM, EL.POTENCIA," +
			" ELU.DIA_SEMANA_LIGADO, ELU.TEMPO_DIARIO_LIGADO" +
			" FROM ELETRODOMESTICO EL" +
            " INNER JOIN ELETRO_USER ELU ON ELU.ELETRODOMESTICO_ID = EL.ID" +
            " WHERE EL.ID = :id", nativeQuery = true)
   public Optional<Eletrodomestico> findById(@Param("id") Long id);
	
	
	@Query(value = "SELECT NOME" +
			" FROM ELETRODOMESTICO" +
			" WHERE TIPO_ELETRODOMESTICO_ID = :id" +
			" ORDER BY NOME ASC", nativeQuery = true)	
	public List<String> findAllNomeByTipoEletrodomestico(@Param("id") Long id);
	
	
	@Query(value = "SELECT MARCA" +
			" FROM ELETRODOMESTICO" +
			" WHERE REMOVER_ACENTOS(NOME) ILIKE (CONCAT('%', REMOVER_ACENTOS(:nome), '%'))" +
			" ORDER BY MARCA ASC", nativeQuery = true)	
	public List<String> findAllMarcaByNomeEletro(@Param("nome") String nome);
	
	
	@Query(value = "SELECT MODELO" +
			" FROM ELETRODOMESTICO" +
			" WHERE REMOVER_ACENTOS(MARCA) ILIKE (CONCAT('%', REMOVER_ACENTOS(:marca), '%'))" +
			" AND REMOVER_ACENTOS(NOME) ILIKE (CONCAT('%', REMOVER_ACENTOS(:nome), '%'))" +
			" ORDER BY MODELO ASC", nativeQuery = true)	
	public List<String> findAllModeloByMarcaAndNomeEletro(@Param("marca") String marca, String nome);
	
	
	@Query(value = "SELECT VOLTAGEM" +
			" FROM ELETRODOMESTICO" +
			" WHERE REMOVER_ACENTOS(MODELO) ILIKE (CONCAT('%', REMOVER_ACENTOS(:modelo), '%'))" +
			" ORDER BY VOLTAGEM ASC", nativeQuery = true)	
	public List<Integer> findAllVoltagemByModelo(@Param("modelo") String modelo);
	
	
	@Query(value = "SELECT ID FROM ELETRODOMESTICO" +
			 " WHERE TIPO_ELETRODOMESTICO_ID = :id" +
			 " AND REMOVER_ACENTOS(NOME) ILIKE (CONCAT('%', REMOVER_ACENTOS(:nome), '%'))" +
			 " AND REMOVER_ACENTOS(MARCA) ILIKE (CONCAT('%', REMOVER_ACENTOS(:marca), '%'))" +
			 " AND REMOVER_ACENTOS(MODELO) ILIKE (CONCAT('%', REMOVER_ACENTOS(:modelo), '%'))" +
			 " AND VOLTAGEM = :voltagem", nativeQuery = true)
	public Long findByNameMarcaModeloVoltagem(@Param("id") Long id, @Param("nome") String nome, @Param("marca") String marca, @Param("modelo") String modelo, @Param("voltagem") Integer voltagem);


}
