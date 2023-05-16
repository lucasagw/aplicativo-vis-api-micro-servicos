package api.vis.eletrodomestico.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import api.vis.eletrodomestico.model.form.EletrodomesticoForm;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "eletrodomestico")
@Data
public class Eletrodomestico {
	/**
	 * Onde "potencia" é a potência do eletrodoméstico em watts, "tempoDiario" é o
	 * tempo de funcionamento diário em horas e "tarifa" é a tarifa de energia
	 * elétrica em reais por kilowatt-hora. A resposta da API será um objeto JSON
	 * contendo o consumo diário, o consumo mensal e o gasto mensal.
	 * 
	 * Para calcular a potência de um produto elétrico, é necessário verificar a tensão e a corrente elétrica 
	 * que ele requer para funcionar. Em seguida, basta utilizar a fórmula:
     *
	 * Potência = Tensão x Corrente
	 * 
	 * Por exemplo, se um produto elétrico requer uma tensão de 110V e uma corrente de 2A para funcionar, 
	 * a potência será: Potência = 110V x 2A = 220W
	 *
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eletrodomestico_id_seq")
	@SequenceGenerator(name = "eletrodomestico_id_seq", sequenceName = "eletrodomestico_id_seq", initialValue = 1, allocationSize = 1)
	private Long id;

	@Column(name = "nome", nullable = false)
	private String nome;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo_eletrodomestico_id", referencedColumnName = "id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private TipoEletrodomestico tipoEletrodomestico;
	
	@Column(name = "marca", nullable = false)
	private String marca;
	
	@Column(name = "modelo", nullable = false)
	private String modelo;
	
	@Column(name = "voltagem", nullable = false)
	private Integer voltagem;

	@Column(name = "potencia", nullable = false)
	private BigDecimal potencia;

	@OneToMany(mappedBy = "eletrodomestico", fetch = FetchType.LAZY)
	private List<EletroUser> eletrosUser = new ArrayList<>();

	public Eletrodomestico() {
		super();
	}

	public Eletrodomestico(Long id) {
		super();
		this.id = id;
	}

	public Eletrodomestico(EletrodomesticoForm form) {
		this.nome = form.getNome();
		this.tipoEletrodomestico = new TipoEletrodomestico(form.getTipoEletrodomesticoId());
		this.marca = form.getMarca();
		this.modelo = form.getModelo();
		this.voltagem = form.getVoltagem();
		this.potencia = form.getPotencia();

	}

}
