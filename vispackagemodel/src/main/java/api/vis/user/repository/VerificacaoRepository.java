package api.vis.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import api.vis.user.model.Verificacao;

@Repository
@Transactional
public interface VerificacaoRepository extends JpaRepository<Verificacao, Long>{

}
