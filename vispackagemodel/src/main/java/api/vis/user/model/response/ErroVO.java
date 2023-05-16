package api.vis.user.model.response;

public class ErroVO {

	private String mensagem;
	
	public ErroVO(String mensagem) {
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
