package api.vis.authorizationserver.model.dto;

public class UsuarioDetailsDto {

	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private String codigoVerificacao;

	public UsuarioDetailsDto() {
		super();
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

	@Override
	public String toString() {
		return "UsuarioDetailsDto [id=" + id + ", nome=" + nome + ", email=" + email + ", telefone=" + telefone
				+ ", codigoVerificacao=" + codigoVerificacao + "]";
	}

}
