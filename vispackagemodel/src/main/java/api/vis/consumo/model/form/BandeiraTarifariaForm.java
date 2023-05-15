package api.vis.consumo.model.form;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BandeiraTarifariaForm {

	private Integer total;

	private List<Item> items;

	public BandeiraTarifariaForm() {
		super();
	}

	public BandeiraTarifariaForm(Integer total, List<Item> items) {
		super();
		this.total = total;
		this.items = items;
	}

}
