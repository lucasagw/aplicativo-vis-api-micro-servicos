package api.vis.eletrodomestico.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.vis.eletrodomestico.mapper.EletrodomesticoMapper;
import api.vis.eletrodomestico.model.Eletrodomestico;
import api.vis.eletrodomestico.model.TipoEletrodomestico;
import api.vis.eletrodomestico.model.form.AssociarEletroUserForm;
import api.vis.eletrodomestico.model.form.EletrodomesticoForm;
import api.vis.eletrodomestico.model.response.EletroMarcaVO;
import api.vis.eletrodomestico.model.response.EletroModeloVO;
import api.vis.eletrodomestico.model.response.EletroNomeVO;
import api.vis.eletrodomestico.model.response.EletroUserVO;
import api.vis.eletrodomestico.model.response.EletroVoltagemVO;
import api.vis.eletrodomestico.model.response.EletrodomesticoVO;
import api.vis.eletrodomestico.repository.EletrodomesticoRepository;
import api.vis.eletrodomestico.repository.TipoEletrodomesticoRepository;
import api.vis.util.VisAquaeConstantes.Entity;
import jakarta.transaction.Transactional;

@Service
public class EletrodomesticoService {

	@Autowired
	private EletrodomesticoRepository repository;
	
	@Autowired
	private EletroUserService eletroUserService;
	
	@Autowired
	private TipoEletrodomesticoRepository tipoEletrodomesticoRepository;

	@Transactional(rollbackOn = Exception.class)
	public EletrodomesticoVO criar(EletrodomesticoForm form) {

		Eletrodomestico eletroCriado = repository.save(new Eletrodomestico(form));
		
		eletroUserService.registerEletroUser(form.getUsuarioId(), eletroCriado, form.getDiaSemanaLigado(), form.getTempoDiarioLigado());
		
		return EletrodomesticoMapper.convertToVo(repository.save(eletroCriado));	
	}

	@Transactional(rollbackOn = Exception.class)
	public EletrodomesticoVO editar(EletrodomesticoForm form, Long id) {

		Eletrodomestico eletro = repository.findById(id).orElseThrow(() -> new NoSuchElementException(Entity.ELETRODOMESTICO));

		eletroUserService.excluirRelacionamentoEletroUser(eletro.getId(), form.getUsuarioId());
		
		eletro.setTipoEletrodomestico(new TipoEletrodomestico(form.getTipoEletrodomesticoId()));
		eletro.setNome(form.getNome());
		eletro.setMarca(form.getMarca());
		eletro.setModelo(form.getModelo());
		eletro.setVoltagem(form.getVoltagem());
		eletro.setPotencia(form.getPotencia());
		eletroUserService.registerEletroUser(form.getUsuarioId(), eletro, form.getDiaSemanaLigado(), form.getTempoDiarioLigado());
		
		return EletrodomesticoMapper.convertToVo(repository.save(eletro));
	}

	@Transactional(rollbackOn = Exception.class)
	public void excluir(Long eletroId, Long userId) {

		Eletrodomestico eletro = repository.findById(eletroId).orElseThrow(() -> new NoSuchElementException(Entity.ELETRODOMESTICO));

		eletroUserService.excluirRelacionamentoEletroUser(eletro.getId(), userId);		
	}
	
	@Transactional(rollbackOn = Exception.class)
	public void excluir(Long id) {
		
		Eletrodomestico eletro = repository.findById(id).orElseThrow(() -> new NoSuchElementException(Entity.ELETRODOMESTICO));
		
		repository.deleteById(eletro.getId());		
	}
	
	public EletrodomesticoVO findById(Long id) {

		return EletrodomesticoMapper.convertToVo(repository.findById(id).orElseThrow(() -> new NoSuchElementException(Entity.ELETRODOMESTICO)));
	}

	public List<EletroUserVO> findByUsuarioId(Long id) {
		
		return eletroUserService.findByUsuarioId(id);
	}
	
	public List<TipoEletrodomestico> findAllTipoEletrodomestico() {
		
		return tipoEletrodomesticoRepository.findAll();
	}

	public List<EletroNomeVO> findAllNomeByTipoEletrodomestico(Long id) {
	
		return EletrodomesticoMapper.convertToNomeVo(repository.findAllNomeByTipoEletrodomestico(id));
	}

	public List<EletroMarcaVO> findAllMarcaByNomeEletro(String nome) {
		
		return EletrodomesticoMapper.convertToMarcaVo(repository.findAllMarcaByNomeEletro(nome));
	}

	public List<EletroModeloVO> findAllModeloByMarcaAndNomeEletro(String marca, String nome) {
		
		return EletrodomesticoMapper.convertToModeloVo(repository.findAllModeloByMarcaAndNomeEletro(marca, nome));
	}

	public List<EletroVoltagemVO> findAllVoltagemByModelo(String modelo) {
	
		return EletrodomesticoMapper.convertToVoltagemVo(repository.findAllVoltagemByModelo(modelo));
	}

	public void associarEletroUser(AssociarEletroUserForm form) {

		Long eletroId = repository.findByNameMarcaModeloVoltagem(form.getTipoEletrodomesticoId(), form.getNome(), form.getMarca(), form.getModelo(), form.getVoltagem());
		
		eletroUserService.registerEletroUser(form.getUsuarioId(), eletroId, form.getDiaSemanaLigado(), form.getTempoDiarioLigado());
	}
		
}
