package api.vis.eletrodomestico.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tipo_eletrodomestico")
@Data
public class TipoEletrodomestico {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_eletrodomestico_id_seq")
	@SequenceGenerator(name = "tipo_eletrodomestico_id_seq", sequenceName = "tipo_eletrodomestico_id_seq", initialValue = 1, allocationSize = 1)
	private Long id;

	@Column(name = "nome", nullable = false)
	private String nome;

	public TipoEletrodomestico() {
		super();
	}

	public TipoEletrodomestico(Long id) {
		super();
		this.id = id;
	}

}
