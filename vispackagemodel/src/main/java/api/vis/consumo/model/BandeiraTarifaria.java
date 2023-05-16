package api.vis.consumo.model;

import java.time.LocalDate;

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
@Table(name = "bandeira_tarifaria")
@Data
public class BandeiraTarifaria {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bandeira_tarifaria_id_seq")
	@SequenceGenerator(name = "bandeira_tarifaria_id_seq", sequenceName = "bandeira_tarifaria_id_seq", initialValue = 1, allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bandeira_id", referencedColumnName = "id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Bandeira bandeira;

	@Column(name = "data")
	private LocalDate month;

	@Column(name = "valor")
	private Double value;

	public BandeiraTarifaria() {
		super();
	}

	public BandeiraTarifaria(Long id) {
		super();
		this.id = id;
	}

	public BandeiraTarifaria(Bandeira bandeira, LocalDate month, Double value) {
		super();
		this.bandeira = bandeira;
		this.month = month;
		this.value = value;
	}

	@Override
	public String toString() {
		return "BandeiraTarifaria [id=" + id + ", bandeira=" + bandeira + ", month=" + month + ", value=" + value + "]";
	}
	
}
