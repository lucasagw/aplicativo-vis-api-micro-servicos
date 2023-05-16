package api.vis.authorizationserver.model;

public class UsuarioDto {

	private Long id;

	private String telefone;

	private String senha;

	private String role;

	private boolean flagAtivo;

	public UsuarioDto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isFlagAtivo() {
		return flagAtivo;
	}

	public void setFlagAtivo(boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

	@Override
	public String toString() {
		return "UsuarioDto [id=" + id + ", telefone=" + telefone + ", senha=" + senha + ", role=" + role
				+ ", flagAtivo=" + flagAtivo + "]";
	}

}