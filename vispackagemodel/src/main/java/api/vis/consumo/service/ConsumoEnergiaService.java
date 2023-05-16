package api.vis.consumo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.vis.consumo.mapper.ConsumoEnergiaMapper;
import api.vis.consumo.model.BandeiraTarifaria;
import api.vis.consumo.model.ConsumoEnergia;
import api.vis.consumo.model.response.ConsumoEnergiaMetricaVO;
import api.vis.consumo.model.response.ConsumoEnergiaVO;
import api.vis.consumo.repository.ConsumoEnergiaRepository;
import api.vis.eletrodomestico.model.EletroUser;
import api.vis.eletrodomestico.model.response.EletroUserVO;
import api.vis.eletrodomestico.service.EletroUserService;
import api.vis.util.UtilService;
import jakarta.transaction.Transactional;

@Service
public class ConsumoEnergiaService {

	@Autowired
	ConsumoEnergiaRepository consumoRepository;
	
	@Autowired
	private EletroUserService eletroUserService;
	
	@Autowired
	private UtilService utilService;
	
	public ConsumoEnergiaVO calcularConsumoDiario(Long eletroId, Long userId) {

		return utilService.calcularConsumoDiario(eletroUserService.findByEletroUsuario(eletroId, userId));
	}

	public ConsumoEnergiaVO calcularConsumoMensal(Long eletroId, Long userId) {

		return utilService.calcularConsumoMensal(eletroUserService.findByEletroUsuario(eletroId, userId));
	}

	public ConsumoEnergiaVO calcularCustoMedioMensal(Long eletroId, Long userId) {

		return utilService.calcularCustoMedioMensal(eletroUserService.findByEletroUsuario(eletroId, userId));
	}

	public ConsumoEnergiaVO calcularMetricaEletroUser(Long eletroId, Long userId) {

		EletroUserVO eletroUser = eletroUserService.findByEletroUsuario(eletroId, userId);

		ConsumoEnergiaVO metricaGeral = new ConsumoEnergiaVO();
		metricaGeral.setConsumoDiario(utilService.calcularConsumoDiario(eletroUser).getConsumoDiario());
		metricaGeral.formatarConsumoDiario(metricaGeral.getConsumoDiario(), eletroUser.getTempoDiarioLigado());
		metricaGeral.setConsumoSemana(utilService.calcularConsumoDiario(eletroUser).getConsumoSemana());
		metricaGeral.formatarConsumoSemana(metricaGeral.getConsumoSemana(), eletroUser.getTempoDiarioLigado(), eletroUser.getDiaSemanaLigado());
		metricaGeral.setConsumoMensal(utilService.calcularConsumoMensal(eletroUser).getConsumoMensal());
		ConsumoEnergiaVO custoMedioMensal = utilService.calcularCustoMedioMensal(eletroUser);
		metricaGeral.formatarConsumoMensal(metricaGeral.getConsumoMensal(), eletroUser.getTempoDiarioLigado(), custoMedioMensal.getLengthOfMonth());
		metricaGeral.setCustoMedioMensal(custoMedioMensal.getCustoMedioMensal());
		metricaGeral.setCustoMedioMensalFormat(metricaGeral.formatarCustoMedioMensal(metricaGeral.getCustoMedioMensal()));
		metricaGeral.setLengthOfMonth(custoMedioMensal.getLengthOfMonth());
		metricaGeral.setLengthOfMonthFormat(metricaGeral.formatarLengthOfMonth(metricaGeral.getLengthOfMonth()));
		metricaGeral.setBandeiraTarifa(utilService.findBandeiraTarifariaByMonth());
		metricaGeral.setEletroUser(eletroUser);
		
		salvarMetricaGeral(metricaGeral);

		return metricaGeral;
	}
	
	@Transactional(rollbackOn = Exception.class)
	private void salvarMetricaGeral(ConsumoEnergiaVO metricaGeral) {
		
		ConsumoEnergia metrica = consumoRepository.findByEletroUserEletrodomesticoIdAndEletroUserUsuarioId(metricaGeral.getEletroUser().getEletroId(),
																								 metricaGeral.getEletroUser().getUsuarioId());
		
		if(!UtilService.isEmpty(metrica)) {
			
			metrica.setBandeiraTarifa(new BandeiraTarifaria(metricaGeral.getBandeiraTarifa().getId()));
			metrica.setEletroUser(new EletroUser(metricaGeral.getEletroUser().getId()));
			metrica.setConsumoDiario(metricaGeral.getConsumoDiario());
			metrica.setConsumoSemana(metricaGeral.getConsumoSemana());
			metrica.setConsumoMensal(metricaGeral.getConsumoMensal());
			metrica.setCustoMedioMensal(metricaGeral.getCustoMedioMensal());
			metrica.setDataHoraCalculo(new Timestamp(System.currentTimeMillis()));
			metrica.setLengthOfMonth(metricaGeral.getLengthOfMonth());
			
			consumoRepository.save(metrica);
		}
		else {
		
			consumoRepository.save(new ConsumoEnergia(new BandeiraTarifaria(metricaGeral.getBandeiraTarifa().getId()), 
                 								  	  new EletroUser(metricaGeral.getEletroUser().getId()),
                 								  	  metricaGeral.getConsumoDiario(), 
                 								  	  metricaGeral.getConsumoSemana(), 
                 								  	  metricaGeral.getConsumoMensal(), 
                 								  	  metricaGeral.getCustoMedioMensal(),
                 								  	  metricaGeral.getLengthOfMonth()));
		}
	}
	
	public List<ConsumoEnergiaMetricaVO> getMetricaUser(Long id) {
		
		return ConsumoEnergiaMapper.convertToEnergiaMetricaVo(consumoRepository.findByEletroUserUsuarioId(id));
	}
}
