package api.vis.eletrodomestico.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.vis.eletrodomestico.mapper.EletrodomesticoMapper;
import api.vis.eletrodomestico.model.EletroUser;
import api.vis.eletrodomestico.model.Eletrodomestico;
import api.vis.eletrodomestico.model.response.EletroUserVO;
import api.vis.eletrodomestico.repository.EletroUserRepository;
import api.vis.util.VisAquaeConstantes.Entity;

@Service
public class EletroUserService {

	@Autowired
	private EletroUserRepository repository;

	public List<EletroUserVO> findByUsuarioId(Long id) {

		return EletrodomesticoMapper.convertToVo(repository.findByUsuarioId(id).orElseThrow(() -> new NoSuchElementException(Entity.ELETRO_USER)));
	}
	
	public EletroUserVO findByEletroUsuario(Long eletroId, Long userId) {
		
		return EletrodomesticoMapper.convertToVo(repository.findByEletroUsuario(eletroId, userId));
	}

	public void registerEletroUser(Long usuarioId, Eletrodomestico eletroCriado, Integer diaSemanaLigado,
			Integer tempoDiarioLigado) {

		repository.save(new EletroUser(usuarioId, eletroCriado, diaSemanaLigado, tempoDiarioLigado));
	}

	public void excluirRelacionamentoEletroUser(Long eletrodomesticoId, Long usuarioId) {

		repository.excluirRelacionamentoEletroUser(eletrodomesticoId, usuarioId);
	}

	public void registerEletroUser(Long usuarioId, Long eletroId, Integer diaSemanaLigado, Integer tempoDiarioLigado) {

		repository.save(new EletroUser(usuarioId, new Eletrodomestico(eletroId), diaSemanaLigado, tempoDiarioLigado));

	}

}
