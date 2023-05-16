package api.vis.authorizationserver.model.dto;

public class ErroDto {

	private String mensagem;
	
	public ErroDto(String mensagem) {
		this.mensagem= mensagem;
		// TODO Auto-generated constructor stub
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
}
