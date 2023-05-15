package api.vis.eletrodomestico.model.form;

import java.math.BigDecimal;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class EletrodomesticoForm {

	@Positive
	@NotNull
	private Long usuarioId;
	
	@Positive
	@NotNull
	private Long tipoEletrodomesticoId;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String marca;
	
	@NotBlank
	private String modelo;
	
	@Positive
	@NotNull
	private Integer voltagem;

	@Positive
	@NotNull
	private BigDecimal potencia;

	@Min(value = 1)
	@Max(value = 7)
	@NotNull
	private Integer diaSemanaLigado;
	
	@Min(value = 1)
	@Max(value = 24)
	@NotNull	
	private Integer tempoDiarioLigado;

	public EletrodomesticoForm() {
		super();
	}

}
