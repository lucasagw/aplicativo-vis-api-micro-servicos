package api.vis.eletrodomestico.model.response;

import api.vis.eletrodomestico.model.Eletrodomestico;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EletroNomeVO {

	private String nome;

	public EletroNomeVO(String nome) {
		super();
		this.nome = nome;
	}

	public EletroNomeVO(Eletrodomestico eletro) {
		super();
		this.nome = eletro.getNome();
	}

}
