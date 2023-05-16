package api.vis.user.model;

import java.sql.Timestamp;
import java.util.Random;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "verificacao")
public class Verificacao {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "verificacao_id_seq")
	@SequenceGenerator(name = "verificacao_id_seq", sequenceName = "verificacao_id_seq", initialValue = 1, allocationSize = 1)
	private Long id;

	@Column(name = "codigo")
	private String codigo = createInt();

	@Column(name = "data_hora_criacao")
	private Timestamp dataHoraCriacao;

	public Verificacao() {
		this.dataHoraCriacao = new Timestamp(System.currentTimeMillis());
	}

	private String createInt() {
		int a = new Random().nextInt(999999);
		String numeroFormatado = String.format("%06d", a);
		return numeroFormatado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Timestamp getDataHoraCriacao() {
		return dataHoraCriacao;
	}

	public void setDataHoraCriacao(Timestamp dataHoraCriacao) {
		this.dataHoraCriacao = dataHoraCriacao;
	}

}
