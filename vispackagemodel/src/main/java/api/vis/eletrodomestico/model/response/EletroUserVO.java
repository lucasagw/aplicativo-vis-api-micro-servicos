package api.vis.eletrodomestico.model.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import api.vis.eletrodomestico.model.EletroUser;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EletroUserVO {

	@JsonIgnore
	private Long id;

	private Long eletroId;

	private Long tipoEletrodomesticoId;

	private String tipoEletrodomesticoNome;

	private String nome;

	private String marca;

	private String modelo;

	private Integer voltagem;

	private BigDecimal potencia;

	private String potenciaFormat;

	private Long usuarioId;

	private Integer diaSemanaLigado;

	private Integer tempoDiarioLigado;

	public EletroUserVO() {
		super();
	}

	public EletroUserVO(EletroUser eletroUser) {
		super();
		this.id = eletroUser.getId();
		this.eletroId = eletroUser.getEletrodomestico().getId();
		this.tipoEletrodomesticoId = eletroUser.getEletrodomestico().getTipoEletrodomestico().getId();
		this.tipoEletrodomesticoNome = eletroUser.getEletrodomestico().getTipoEletrodomestico().getNome();
		this.nome = eletroUser.getEletrodomestico().getNome();
		this.marca = eletroUser.getEletrodomestico().getMarca();
		this.modelo = eletroUser.getEletrodomestico().getModelo();
		this.voltagem = eletroUser.getEletrodomestico().getVoltagem();
		this.potencia = eletroUser.getEletrodomestico().getPotencia();
		this.potenciaFormat = formatarEletroPotencia(potencia);
		this.usuarioId = eletroUser.getUsuarioId();
		this.diaSemanaLigado = eletroUser.getDiaSemanaLigado();
		this.tempoDiarioLigado = eletroUser.getTempoDiarioLigado();

	}

	public EletroUserVO(Long id, Long eletroId, Long tipoEletrodomesticoId, String tipoEletrodomesticoNome, String nome,
			String marca, String modelo, Integer voltagem, BigDecimal potencia, String potenciaFormat, Long usuarioId,
			Integer diaSemanaLigado, Integer tempoDiarioLigado) {
		super();
		this.id = id;
		this.eletroId = eletroId;
		this.tipoEletrodomesticoId = tipoEletrodomesticoId;
		this.tipoEletrodomesticoNome = tipoEletrodomesticoNome;
		this.nome = nome;
		this.marca = marca;
		this.modelo = modelo;
		this.voltagem = voltagem;
		this.potencia = potencia;
		this.potenciaFormat = formatarEletroPotencia(potencia);
		this.usuarioId = usuarioId;
		this.diaSemanaLigado = diaSemanaLigado;
		this.tempoDiarioLigado = tempoDiarioLigado;
	}

	private String formatarEletroPotencia(BigDecimal potencia) {

		return String.format("%.2f W", potencia);
	}

}
