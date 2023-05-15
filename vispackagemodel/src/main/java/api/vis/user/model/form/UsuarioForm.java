package api.vis.user.model.form;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioForm {

	@NotBlank
	private String nome;

	/*
	 * @NotNull private GeneroEnum genero;
	 */

	@NotNull
	private LocalDate dataNascimento;

	@Email(message = "Formato de email incorreto")
	@NotBlank
	private String email;

	@Size(min = 11, max = 13, message = "O telefone está incorreto!")
	@NotBlank
	private String telefone;

	@NotBlank
	private String senha;

	@CPF
	@NotBlank
	private String cpf;

	public UsuarioForm() {
		super();
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
}
