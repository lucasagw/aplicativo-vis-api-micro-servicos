package api.vis.user.model.response;

import java.time.LocalDate;

import api.vis.user.model.Usuario;

public class UsuarioDetailsVO {

	private Long id;
	private String nome;
	private LocalDate dataNascimento;
	private String email;
	private String telefone;
	private boolean flagAtivo;
	private String codigoVerificacao = null;

	public UsuarioDetailsVO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.dataNascimento = usuario.getDataNascimento();
		this.email = usuario.getEmail();
		this.telefone = usuario.getTelefone();
		this.flagAtivo = usuario.isFlagAtivo();
		if (usuario.getVerificacao() != null) {
			this.codigoVerificacao = usuario.getVerificacao().getCodigo();
		}
	}

	public boolean isFlagAtivo() {
		return flagAtivo;
	}

	public void setFlagAtivo(boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCodigoVerificacao() {
		return codigoVerificacao;
	}

	public void setCodigoVerificacao(String codigoVerificacao) {
		this.codigoVerificacao = codigoVerificacao;
	}

}
