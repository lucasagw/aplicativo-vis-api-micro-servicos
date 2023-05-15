package api.vis.consumo.model.response;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BandeiraTarifariaVO {

	private Long id;

	private byte flagType;

	private String flagName;

	private String flagSignificado;

	private LocalDate month;

	private Double value;

	public BandeiraTarifariaVO() {
		super();
	}

	public BandeiraTarifariaVO(Long id, byte flagType, String flagName, String flagSignificado, LocalDate month,
			Double value) {
		super();
		this.id = id;
		this.flagType = flagType;
		this.flagName = flagName;
		this.flagSignificado = flagSignificado;
		this.month = month;
		this.value = value;
	}

}
