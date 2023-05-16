package api.vis.util;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PaginacaoUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Integer> listaPagina;

	private Integer pageSize;

	private Integer numeroRegistroTotal;

	private Integer page;

	private String direction;

	private String properties;

	public PaginacaoUtil() {

	}

	public List<Integer> getListaPagina() {
		return listaPagina;
	}

	public void setListaPagina(List<Integer> listaPagina) {
		this.listaPagina = listaPagina;
	}

	public Integer getPageSize() {
		return UtilService.isEmpty(pageSize) || pageSize == 0 ? 10 : pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getNumeroRegistroTotal() {
		return numeroRegistroTotal;
	}

	public void setNumeroRegistroTotal(Integer numeroRegistroTotal) {
		this.numeroRegistroTotal = numeroRegistroTotal;
	}

	public Integer getPage() {
		return UtilService.isEmpty(page) ? 0 : page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getProperties() {
		return properties;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}
}
