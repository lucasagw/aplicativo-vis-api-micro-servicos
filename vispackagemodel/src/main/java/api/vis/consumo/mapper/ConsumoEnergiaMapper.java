package api.vis.consumo.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import api.vis.consumo.model.ConsumoEnergia;
import api.vis.consumo.model.response.ConsumoEnergiaMetricaVO;
import api.vis.consumo.model.response.ConsumoEnergiaVO;
import api.vis.eletrodomestico.model.response.EletroUserVO;

public class ConsumoEnergiaMapper {

	private ConsumoEnergiaVO consumoDiario(BigDecimal consumoDiario, BigDecimal consumoSemana, EletroUserVO eletroUser) {

		ConsumoEnergiaVO consumo = new ConsumoEnergiaVO();
		
		consumo.setEletroUser(eletroUser);
				
		consumo.setConsumoDiario(consumoDiario);
		
		consumo.setConsumoSemana(consumoSemana);
		
		consumo.formatarConsumoDiario(consumoDiario, eletroUser.getTempoDiarioLigado());
		
		consumo.formatarConsumoSemana(consumoSemana, eletroUser.getTempoDiarioLigado(), eletroUser.getDiaSemanaLigado());
			
		return consumo;
	}

	public ConsumoEnergiaVO convertToConsumoDiarioVo(BigDecimal consumoDiario, BigDecimal consumoSemanal, EletroUserVO eletroUser) {
		
		return this.consumoDiario(consumoDiario, consumoSemanal, eletroUser);
	}

	private ConsumoEnergiaVO consumoMensal(BigDecimal consumoMensal, EletroUserVO eletroUser, int lengthOfMonth) {

		ConsumoEnergiaVO consumo = new ConsumoEnergiaVO();

		consumo.setEletroUser(eletroUser);
		
		consumo.setConsumoMensal(consumoMensal);
		
		consumo.formatarConsumoMensal(consumoMensal, eletroUser.getTempoDiarioLigado(), lengthOfMonth);
		
		consumo.setLengthOfMonth(lengthOfMonth);
		
		consumo.formatarLengthOfMonth(lengthOfMonth);

		return consumo;
	}

	public ConsumoEnergiaVO convertToConsumoMensalVo(BigDecimal consumoMensal, EletroUserVO eletroUser, int lengthOfMonth) {
		
		return this.consumoMensal(consumoMensal, eletroUser, lengthOfMonth);		
	}

	public static List<ConsumoEnergiaMetricaVO> convertToEnergiaMetricaVo(List<ConsumoEnergia> consumos) {
		
		return consumos.stream().map(ConsumoEnergiaMetricaVO::new).collect(Collectors.toList());
	}
}
