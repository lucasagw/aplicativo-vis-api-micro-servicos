package api.vis.eletrodomestico.model.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AssociarEletroUserForm {

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
	private Integer diaSemanaLigado;

	@Positive
	@NotNull
	private Integer tempoDiarioLigado;

	public AssociarEletroUserForm() {
		super();
	}

}
