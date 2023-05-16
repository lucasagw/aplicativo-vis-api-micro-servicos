package api.vis.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Collection;

import org.apache.commons.math3.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.vis.consumo.mapper.BandeiraTarifaMapper;
import api.vis.consumo.mapper.ConsumoEnergiaMapper;
import api.vis.consumo.model.TarifaCoelba;
import api.vis.consumo.model.response.BandeiraTarifariaVO;
import api.vis.consumo.model.response.ConsumoEnergiaVO;
import api.vis.consumo.repository.BandeiraTarifariaRepository;
import api.vis.eletrodomestico.model.response.EletroUserVO;
import api.vis.util.VisAquaeConstantes.TarifaCoelba2023;

@Service
public class UtilService {
	
	@Autowired
	private BandeiraTarifariaRepository bandeiraTarifaRepository;
	
	
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object value) {

		if (value == null) {
			return true;
		} else if (value instanceof Collection) {
			return ((Collection) value).isEmpty();
		}
		return false;
	}

	public static boolean isNotEmpty(String str) {

		return (str != null && str.length() > 0);
	}

	public ConsumoEnergiaVO calcularConsumoDiario(EletroUserVO eletroUser) {

		BigDecimal consumoDiario = eletroUser.getPotencia()
										 .multiply(new BigDecimal(eletroUser.getTempoDiarioLigado()))
										 .divide(BigDecimal.valueOf(1000))
			                             .setScale(4, RoundingMode.HALF_UP); // Define a escala para 4 casas decimai 
	
		BigDecimal consumoSemana = consumoDiario.multiply(new BigDecimal(eletroUser.getDiaSemanaLigado()));
		
		return new ConsumoEnergiaMapper().convertToConsumoDiarioVo(consumoDiario, consumoSemana, eletroUser);
	}
	public ConsumoEnergiaVO calcularConsumoMensal(EletroUserVO eletroUser) {

		return new ConsumoEnergiaMapper().convertToConsumoMensalVo(
				calcularConsumoDiario(eletroUser).getConsumoDiario().multiply(new BigDecimal(LocalDate.now().lengthOfMonth())), eletroUser, LocalDate.now().lengthOfMonth());
	}

	public ConsumoEnergiaVO calcularCustoMedioMensal(EletroUserVO eletroUser){

		BigDecimal consumo = calcularConsumoMensal(eletroUser).getConsumoMensal();
		
		TarifaCoelba tarifaCoelba = aplicarTarifaCoelba(consumo);
		
		return new ConsumoEnergiaVO(consumo.multiply(tarifaCoelba.getTarifa()), tarifaCoelba.getConsumo(), tarifaCoelba.getTarifa(), 
				 LocalDate.now().lengthOfMonth(), eletroUser);
	}

	private TarifaCoelba aplicarTarifaCoelba(BigDecimal consumo) {

		Pair<BigDecimal, BigDecimal>[] limitesDeConsumoPorFaixa = faixas();

		BigDecimal[] tarifasPorFaixa = new BigDecimal[] { TarifaCoelba2023.CONSUMO_FAIXA_1,
				TarifaCoelba2023.CONSUMO_FAIXA_2, TarifaCoelba2023.CONSUMO_FAIXA_3 };

		BigDecimal tarifa = null;
		int faixasPercorridas = 0;

		// Itera sobre as faixas de consumo para encontrar a tarifa correta
		for (Pair<BigDecimal, BigDecimal> limiteDeConsumo : limitesDeConsumoPorFaixa) {
			BigDecimal limiteInferior = limiteDeConsumo.getFirst();
			BigDecimal limiteSuperior = limiteDeConsumo.getSecond();

			// Verifica se o consumo está dentro do limite da faixa atual
			if ((limiteInferior == null || consumo.compareTo(limiteInferior) >= 0)
					&& (limiteSuperior == null || consumo.compareTo(limiteSuperior) < 0)) {
				tarifa = tarifasPorFaixa[faixasPercorridas];
				break;
			}
			faixasPercorridas++;
		}

		// Se não encontrou nenhuma tarifa, lança uma exceção
		if (tarifa == null) {
			throw new IllegalArgumentException("Consumo fora do intervalo de tarifas.");
		}

		// Retorna uma nova instância de TarifaCoelba com o consumo e a tarifa corretos
		return new TarifaCoelba(consumo, tarifa);
	}

	private Pair[] faixas() {
		return new Pair[] { new Pair<BigDecimal, BigDecimal>(null, new BigDecimal("150")),
				new Pair<BigDecimal, BigDecimal>(new BigDecimal("151"), new BigDecimal("500")),
				new Pair<BigDecimal, BigDecimal>(new BigDecimal("501"), null) };
	}

	public BandeiraTarifariaVO findBandeiraTarifariaByMonth() {
		
		return BandeiraTarifaMapper.convertToBandeiraTarifariaVo(bandeiraTarifaRepository.findByMonth(LocalDate.now().getMonth().getValue()));
	}		
}
