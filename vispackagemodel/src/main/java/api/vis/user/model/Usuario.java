package api.vis.user.model;

import java.time.LocalDate;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import api.vis.user.model.form.UsuarioForm;
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

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_id_seq")
	@SequenceGenerator(name = "usuario_id_seq", sequenceName = "usuario_id_seq", initialValue = 1, allocationSize = 1)
	private Long id;

	@Column(name = "nome")
	private String nome;

	/*@Convert(converter = ConverteGenero.class)
	@Column(name = "genero")
	private GeneroEnum genero;*/

	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@Column(name = "data_nascimento", columnDefinition = "DATE")
	private LocalDate dataNascimento;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "telefone")
	private String telefone;

	@Column(name = "avatar")
	private String avatar;

	@Column(name = "senha", unique = true)
	private String senha;

	@Column(name = "cpf", unique = true)
	private String cpf;

	@Column(name = "flag_ativo")
	private boolean flagAtivo;

	@Column(name = "primeiro_login")
	private boolean primeiroLogin;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "verificacao_id", referencedColumnName = "id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Verificacao verificacao;

	public Usuario() {
		super();
	}

	public Usuario(Long id) {
		super();
		this.id = id;
	}

	public Usuario(UsuarioForm form, BCryptPasswordEncoder bCrypt, Verificacao verificacao) {
		this.nome = form.getNome();
		this.dataNascimento = form.getDataNascimento();
		this.email = form.getEmail();
		this.telefone = form.getTelefone();
		this.senha = bCrypt.encode(form.getSenha());
		this.cpf = form.getCpf();
		this.primeiroLogin = true;
		this.flagAtivo = false;
		this.verificacao = verificacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public boolean isFlagAtivo() {
		return flagAtivo;
	}

	public void setFlagAtivo(boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

	public boolean isPrimeiroLogin() {
		return primeiroLogin;
	}

	public void setPrimeiroLogin(boolean primeiroLogin) {
		this.primeiroLogin = primeiroLogin;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Verificacao getVerificacao() {
		return verificacao;
	}

	public void setVerificacao(Verificacao verificacao) {
		this.verificacao = verificacao;
	}
}