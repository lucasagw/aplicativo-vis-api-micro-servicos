package api.vis.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import api.vis.user.model.Usuario;


@Repository
@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByTelefone(String telefone);
	
	Optional<Usuario> findByEmail(String email);
	
	
	@Query(value = "SELECT * FROM PUBLIC.USUARIO WHERE CPF = :cpf OR EMAIL = :email OR TELEFONE = :telefone", nativeQuery = true)
	List<Usuario> customFindByCpfEmailOrTel(@Param("cpf") String cpf, @Param("email") String email , @Param("telefone") String telefone);

}
