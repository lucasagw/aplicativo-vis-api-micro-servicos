package api.vis.eletrodomestico.model.response;

import api.vis.eletrodomestico.model.Eletrodomestico;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EletroMarcaVO {

	private String marca;

	public EletroMarcaVO() {
		super();
	}

	public EletroMarcaVO(String marca) {
		super();
		this.marca = marca;
	}

	public EletroMarcaVO(Eletrodomestico eletro) {
		super();
		this.marca = eletro.getMarca();
	}

}
