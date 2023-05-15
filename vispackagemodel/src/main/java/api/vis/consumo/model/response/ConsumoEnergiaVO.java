package api.vis.consumo.model.response;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import api.vis.eletrodomestico.model.response.EletroUserVO;
import api.vis.util.VisAquaeConstantes.Console;
import lombok.Data;

@Data
public class ConsumoEnergiaVO {

	@JsonInclude(Include.NON_NULL)
	private BigDecimal consumoDiario;

	@JsonInclude(Include.NON_NULL)
	private String consumoDiarioFormat;

	@JsonInclude(Include.NON_NULL)
	private BigDecimal consumoSemana;

	@JsonInclude(Include.NON_NULL)
	private String consumoSemanaFormat;

	@JsonInclude(Include.NON_NULL)
	private BigDecimal consumoMensal;

	@JsonInclude(Include.NON_NULL)
	private String consumoMensalFormat;

	@JsonInclude(Include.NON_NULL)
	private int lengthOfMonth;

	@JsonInclude(Include.NON_NULL)
	private String lengthOfMonthFormat;

	@JsonInclude(Include.NON_NULL)
	private BigDecimal custoMedioMensal;

	@JsonInclude(Include.NON_NULL)
	private String custoMedioMensalFormat;

	@JsonInclude(Include.NON_NULL)
	private BandeiraTarifariaVO bandeiraTarifa;

	@JsonInclude(Include.NON_NULL)
	private EletroUserVO eletroUser;

	@JsonInclude(Include.NON_NULL)
	private BigDecimal tarifaFaixaCoelba;

	@JsonInclude(Include.NON_NULL)
	private String tarifaFaixaCoelbaFormat;

	@JsonInclude(Include.NON_NULL)
	private String console;

	public ConsumoEnergiaVO() {
		super();
	}

	public ConsumoEnergiaVO(BigDecimal custoMedioMensal, BigDecimal consumoMensal, BigDecimal tarifaFaixaCoelba,
			int lengthOfMonth, EletroUserVO eletroUser) {
		super();
		this.console = String.format(Console.CUSTO_MEDIO_MENSAL, eletroUser.getNome(), eletroUser.getMarca(), eletroUser.getModelo(),
	    eletroUser.getVoltagem(), eletroUser.getPotencia(), lengthOfMonth, tarifaFaixaCoelba, formatarCustoMedioMensal(custoMedioMensal));	 
		/*this.console = MessageFormat.format(Console.CUSTO_MEDIO_MENSAL, eletroUser.getNome(), eletroUser.getMarca(), 
	    eletroUser.getModelo(), eletroUser.getVoltagem(),eletroUser.getPotencia(), lengthOfMonth, tarifaFaixaCoelba, custoMedioMensal);*/
		this.eletroUser = eletroUser;
		this.custoMedioMensal = custoMedioMensal;
		this.custoMedioMensalFormat = formatarCustoMedioMensal(custoMedioMensal);
		this.consumoMensal = consumoMensal;
		this.consumoMensalFormat = formatarConsumoMensal(consumoMensal, eletroUser.getTempoDiarioLigado(), lengthOfMonth);
		this.lengthOfMonth = lengthOfMonth;
		this.lengthOfMonthFormat = formatarLengthOfMonth(lengthOfMonth);
		this.tarifaFaixaCoelba = tarifaFaixaCoelba;
		this.tarifaFaixaCoelbaFormat = formatarTarifaFaixaCoelba(tarifaFaixaCoelba);
	}

	public String formatarLengthOfMonth(int lengthOfMonth) {

		this.lengthOfMonthFormat = String.format("%d dias", lengthOfMonth);
		
		return lengthOfMonthFormat;
	}

	public String formatarTarifaFaixaCoelba(BigDecimal tarifaFaixaCoelba) {

		return String.format("R$%.5f/kWh", tarifaFaixaCoelba);
	}

	public String formatarCustoMedioMensal(BigDecimal custoMedioMensal) {

		DecimalFormat df = new DecimalFormat("#,##0.00");

		return String.format("R$%s", df.format(custoMedioMensal));
	}

	public String formatarConsumoDiario(BigDecimal consumo, int tempoDiarioLigado) {

		this.consumoDiarioFormat = String.format(Console.FORMATO_CONSUMO_DIARIO, consumo, tempoDiarioLigado);

		return consumoDiarioFormat;
	}

	public String formatarConsumoSemana(BigDecimal consumo, int tempoDiarioLigado, int diaSemanaLigado) {

		this.consumoSemanaFormat = String.format(Console.FORMATO_CONSUMO_SEMANA, consumo, tempoDiarioLigado, diaSemanaLigado);
		
		return consumoSemanaFormat;
	}

	public String formatarConsumoMensal(BigDecimal consumo, int tempoDiarioLigado, int lengthOfMonth) {

		this.consumoMensalFormat = String.format(Console.FORMATO_CONSUMO_MENSAL, consumo, tempoDiarioLigado, lengthOfMonth);

		return consumoMensalFormat;
	}

}
