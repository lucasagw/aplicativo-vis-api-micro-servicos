package api.vis.eletrodomestico.model.response;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EletrodomesticoVO {

	private Long id;

	private Long tipoEletrodomesticoId;

	private String tipoEletrodomesticoNome;

	private String nome;

	private String marca;

	private String modelo;

	private Integer voltagem;

	private BigDecimal potencia;
	
	private String potenciaFormat;

	public EletrodomesticoVO() {
		super();
	}

	public EletrodomesticoVO(Long id, Long tipoEletrodomesticoId, String tipoEletrodomesticoNome, String nome,
			String marca, String modelo, Integer voltagem, BigDecimal potencia, String potenciaFormat) {
		super();
		this.id = id;
		this.tipoEletrodomesticoId = tipoEletrodomesticoId;
		this.tipoEletrodomesticoNome = tipoEletrodomesticoNome;
		this.nome = nome;
		this.marca = marca;
		this.modelo = modelo;
		this.voltagem = voltagem;
		this.potencia = potencia;
		this.potenciaFormat = formatarEletroPotencia(potencia);
		
	}
	
	private String formatarEletroPotencia(BigDecimal potencia) {

		return String.format("%.2f W", potencia);
	}
}
