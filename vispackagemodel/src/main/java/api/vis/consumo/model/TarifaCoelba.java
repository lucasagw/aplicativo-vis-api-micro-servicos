package api.vis.consumo.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TarifaCoelba {

	private BigDecimal consumo;

	private BigDecimal tarifa;

	public TarifaCoelba() {
		super();
	}

	public TarifaCoelba(BigDecimal consumo, BigDecimal tarifa) {
		super();
		this.consumo = consumo;
		this.tarifa = tarifa;

	}

}
