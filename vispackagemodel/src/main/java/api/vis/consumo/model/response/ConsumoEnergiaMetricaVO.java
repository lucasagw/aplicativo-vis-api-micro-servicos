package api.vis.consumo.model.response;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import api.vis.consumo.model.ConsumoEnergia;
import api.vis.util.VisAquaeConstantes.Console;
import lombok.Data;

@Data
public class ConsumoEnergiaMetricaVO {

	private byte flagType;

	private String flagName;

	private String flagSignificado;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate flagMonth;

	private Double flagValue;

	private BigDecimal consumoDiario;

	private String consumoDiarioFormat;

	private BigDecimal consumoSemana;

	private String consumoSemanaFormat;

	private BigDecimal consumoMensal;

	private String consumoMensalFormat;

	private BigDecimal custoMedioMensal;

	private String custoMedioMensalFormat;

	private String nome;

	private String marca;

	private String modelo;

	private Integer voltagem;

	private BigDecimal potencia;

	private Integer diaSemanaLigado;

	private Integer tempoDiarioLigado;

	private int lengthOfMonth;

	private String lengthOfMonthFormat;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-dd HH:mm:ss", timezone = "America/Sao_Paulo")
	private Timestamp dataHoraCalculo;

	public ConsumoEnergiaMetricaVO() {
		super();
	}

	public ConsumoEnergiaMetricaVO(ConsumoEnergia consumo) {
		super();
		this.flagType = consumo.getBandeiraTarifa().getBandeira().getFlagType();
		this.flagName = consumo.getBandeiraTarifa().getBandeira().getNome();
		this.flagSignificado = consumo.getBandeiraTarifa().getBandeira().getSignificado();
		this.flagMonth = consumo.getBandeiraTarifa().getMonth();
		this.flagValue = consumo.getBandeiraTarifa().getValue();
		this.consumoDiario = consumo.getConsumoDiario();
		this.consumoDiarioFormat = formatarConsumoDiario(consumoDiario, consumo.getEletroUser().getTempoDiarioLigado());
		this.consumoSemana = consumo.getConsumoSemana();
		this.consumoSemanaFormat = formatarConsumoSemana(consumoSemana, consumo.getEletroUser().getTempoDiarioLigado(), consumo.getEletroUser().getDiaSemanaLigado());
		this.consumoMensal = consumo.getConsumoMensal();
		this.consumoMensalFormat = formatarConsumoMensal(consumoMensal, consumo.getEletroUser().getTempoDiarioLigado(), consumo.getLengthOfMonth());
		this.custoMedioMensal = consumo.getCustoMedioMensal();
		this.custoMedioMensalFormat = formatarCustoMedioMensal(custoMedioMensal);
		this.nome = consumo.getEletroUser().getEletrodomestico().getNome();
		this.marca = consumo.getEletroUser().getEletrodomestico().getMarca();
		this.modelo = consumo.getEletroUser().getEletrodomestico().getModelo();
		this.voltagem = consumo.getEletroUser().getEletrodomestico().getVoltagem();
		this.potencia = consumo.getEletroUser().getEletrodomestico().getPotencia();
		this.diaSemanaLigado = consumo.getEletroUser().getDiaSemanaLigado();
		this.tempoDiarioLigado = consumo.getEletroUser().getTempoDiarioLigado();
		this.lengthOfMonth = consumo.getLengthOfMonth();
		this.lengthOfMonthFormat = formatarLengthOfMonth(lengthOfMonth);
		this.dataHoraCalculo = consumo.getDataHoraCalculo();
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
