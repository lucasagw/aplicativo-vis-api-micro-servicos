package api.vis.consumo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "bandeira")
@Data
public class Bandeira {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bandeira_id_seq")
	@SequenceGenerator(name = "bandeira_id_seq", sequenceName = "bandeira_id_seq", initialValue = 1, allocationSize = 1)
	private Long id;

	@Column(name = "flag_tipo")
	private byte flagType;

	@Column(name = "nome")
	private String nome;
	
	@Column(name = "significado")
	private String significado;

	public Bandeira() {
		super();
	}

}
