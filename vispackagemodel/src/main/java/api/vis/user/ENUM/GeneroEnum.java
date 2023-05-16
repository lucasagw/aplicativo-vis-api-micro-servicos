package api.vis.user.ENUM;

public enum GeneroEnum {

	FEMININO("FEMININO"), MASCULINO("MASCULINO"), NAO_BINARIO("NÃO-BINÁRIO"), GENERO_FLUIDO("GÊNERO-FLUIDO");

	private String codigo;

	private GeneroEnum(String codigo) {
			this.codigo = codigo;
		}

	public String getCodigo() {
		return codigo;
	}

	public static GeneroEnum valueOfCodigo(String codigo) {
		for (GeneroEnum situacao : values()) {
			if (situacao.getCodigo().equalsIgnoreCase(codigo)) {
				return situacao;
			}
		}
		throw new IllegalArgumentException("Código não encontrado: " + codigo);
	}
}
