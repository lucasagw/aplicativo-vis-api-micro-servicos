package api.vis.eletrodomestico.model.response;

import api.vis.eletrodomestico.model.Eletrodomestico;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EletroVoltagemVO {

	private Integer voltagem;

	public EletroVoltagemVO() {
		super();
	}

	public EletroVoltagemVO(Integer voltagem) {
		super();
		this.voltagem = voltagem;
	}
	
	public EletroVoltagemVO(Eletrodomestico eletro) {
		super();
		this.voltagem = eletro.getVoltagem();
	}

}
