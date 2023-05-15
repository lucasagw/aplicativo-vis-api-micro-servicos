package api.vis.authorizationserver.model.dto;

public class TokenDto {

	private String token;
	private String tipo;
	private String refreshtoken;

	public TokenDto() {
	}

	public TokenDto(String token, String tipo, String refreshtoken) {
		this.token = token;
		this.tipo = tipo;
		this.refreshtoken = refreshtoken;
	}

	public TokenDto(String refreshtoken, String tipo) {
		this.tipo = tipo;
		this.refreshtoken = refreshtoken;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getRefreshtoken() {
		return refreshtoken;
	}

	public void setRefreshtoken(String refreshtoken) {
		this.refreshtoken = refreshtoken;
	}

	@Override
	public String toString() {
		return "TokenDto [token=" + token + ", tipo=" + tipo + ", refreshtoken=" + refreshtoken + "]";
	}

}