package api.vis.user.model.response;

import api.vis.user.model.Usuario;

public class UsuarioVO {

	private Long id;
	private String nome;
	private String senha;
	private Boolean flagAtivo;
	private String telefone;
	private String email;

	public UsuarioVO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.senha = usuario.getSenha();
		this.flagAtivo = usuario.isFlagAtivo();
		this.telefone = usuario.getTelefone();
		this.email = usuario.getEmail();
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getFlagAtivo() {
		return flagAtivo;
	}

	public void setFlagAtivo(Boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
