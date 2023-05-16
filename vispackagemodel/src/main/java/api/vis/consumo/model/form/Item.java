package api.vis.consumo.model.form;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {

	private byte flagType;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate month;

	private Double value;

	public Item() {
		super();
	}

	public Item(byte flagType, LocalDate month, Double value) {
		super();
		this.flagType = flagType;
		this.month = month;
		this.value = value;
	}

	@Override
	public String toString() {
		return "Item [flagType=" + flagType + ", month=" + month + ", value=" + value + "]";
	}

}
