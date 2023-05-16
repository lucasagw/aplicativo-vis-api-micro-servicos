package api.vis.consumo.mapper;

import api.vis.consumo.model.BandeiraTarifaria;
import api.vis.consumo.model.response.BandeiraTarifariaVO;

public class BandeiraTarifaMapper {

	
	public static BandeiraTarifariaVO convertToBandeiraTarifariaVo(BandeiraTarifaria bt) {
		
		return BandeiraTarifariaVO.builder()
								  .id(bt.getId())
								  .flagType(bt.getBandeira().getFlagType())
								  .flagName(bt.getBandeira().getNome())
								  .flagSignificado(bt.getBandeira().getSignificado())
								  .month(bt.getMonth())
								  .value(bt.getValue())
								  .build();
	}

}
