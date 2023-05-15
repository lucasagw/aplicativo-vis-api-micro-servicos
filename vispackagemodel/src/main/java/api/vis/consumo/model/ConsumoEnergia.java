package api.vis.consumo.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import api.vis.eletrodomestico.model.EletroUser;
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
@Table(name = "consumo_energia")
@Data
public class ConsumoEnergia {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "consumo_energia_id_seq")
	@SequenceGenerator(name = "consumo_energia_id_seq", sequenceName = "consumo_energia_id_seq", initialValue = 1, allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bandeira_tarifa_id", referencedColumnName = "id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private BandeiraTarifaria bandeiraTarifa;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "eletro_user_id", referencedColumnName = "id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private EletroUser eletroUser;

	@Column(name = "consumo_diario", nullable = false)
	private BigDecimal consumoDiario;
	
	@Column(name = "consumo_semana", nullable = false)
	private BigDecimal consumoSemana;

	@Column(name = "consumo_mensal", nullable = false)
	private BigDecimal consumoMensal;

	@Column(name = "custo_medio_mensal", nullable = false)
	private BigDecimal custoMedioMensal;
	
	@Column(name = "data_hora_calculo", nullable = false)
	private Timestamp dataHoraCalculo;
	
	@Column(name = "duracao_do_mes", nullable = false)
	private int lengthOfMonth;

	public ConsumoEnergia() {
		super();
	}

	public ConsumoEnergia(BandeiraTarifaria bandeiraTarifa, EletroUser eletroUser,
			BigDecimal consumoDiario, BigDecimal consumoSemana, BigDecimal consumoMensal, BigDecimal custoMedioMensal, int lengthOfMonth) {
		super();
		this.bandeiraTarifa = bandeiraTarifa;
		this.eletroUser = eletroUser;
		this.consumoDiario = consumoDiario;
		this.consumoSemana = consumoSemana;
		this.consumoMensal = consumoMensal;
		this.custoMedioMensal = custoMedioMensal;
		this.dataHoraCalculo = new Timestamp(System.currentTimeMillis());
		this.lengthOfMonth = lengthOfMonth;
	}
}
