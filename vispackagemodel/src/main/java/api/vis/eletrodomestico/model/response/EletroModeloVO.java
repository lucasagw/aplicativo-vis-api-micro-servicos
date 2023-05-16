package api.vis.eletrodomestico.model.response;

import api.vis.eletrodomestico.model.Eletrodomestico;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EletroModeloVO {

	private String modelo;

	public EletroModeloVO() {
		super();
	}

	public EletroModeloVO(String modelo) {
		super();
		this.modelo = modelo;
	}
	
	public EletroModeloVO(Eletrodomestico eletro) {
		super();
		this.modelo = eletro.getModelo();
	}

}
