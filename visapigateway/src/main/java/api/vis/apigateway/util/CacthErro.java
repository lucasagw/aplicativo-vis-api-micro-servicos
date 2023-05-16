package api.vis.apigateway.util;

import java.io.Serializable;

public class CacthErro implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer codigo;

	private String mensagem;

	public CacthErro() {
		super();
	}

	public CacthErro(Integer codigo, String mensagem) {

		super();

		this.codigo = codigo;

		this.mensagem = mensagem;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
