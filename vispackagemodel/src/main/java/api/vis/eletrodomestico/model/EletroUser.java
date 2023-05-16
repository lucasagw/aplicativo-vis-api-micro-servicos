package api.vis.eletrodomestico.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "eletro_user")
@Data
public class EletroUser {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eletro_user_id_seq")
	@SequenceGenerator(name = "eletro_user_id_seq", sequenceName = "eletro_user_id_seq", initialValue = 1, allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "eletrodomestico_id", referencedColumnName = "id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Eletrodomestico eletrodomestico;

	@Column(name = "usuario_id")
	private Long usuarioId;

	@Column(name = "dia_semana_ligado")
	private Integer diaSemanaLigado;

	@Column(name = "tempo_diario_ligado")
	private Integer tempoDiarioLigado;

	public EletroUser() {
		super();
	}

	public EletroUser(Long usuarioId, Eletrodomestico eletrodomestico, Integer diaSemanaLigado,
			Integer tempoDiarioLigado) {
		this.usuarioId = usuarioId;
		this.eletrodomestico = eletrodomestico;
		this.diaSemanaLigado = diaSemanaLigado;
		this.tempoDiarioLigado = tempoDiarioLigado;
	}

	public EletroUser(Long id, Eletrodomestico eletrodomestico) {
		super();
		this.id = id;
		this.eletrodomestico = eletrodomestico;
	}

	public EletroUser(Long id) {
		super();
		this.id = id;
	}

}
