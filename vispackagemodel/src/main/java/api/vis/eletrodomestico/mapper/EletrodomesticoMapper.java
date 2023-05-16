package api.vis.eletrodomestico.mapper;

import java.util.List;
import java.util.stream.Collectors;

import api.vis.eletrodomestico.model.EletroUser;
import api.vis.eletrodomestico.model.Eletrodomestico;
import api.vis.eletrodomestico.model.response.EletroMarcaVO;
import api.vis.eletrodomestico.model.response.EletroModeloVO;
import api.vis.eletrodomestico.model.response.EletroNomeVO;
import api.vis.eletrodomestico.model.response.EletroUserVO;
import api.vis.eletrodomestico.model.response.EletroVoltagemVO;
import api.vis.eletrodomestico.model.response.EletrodomesticoVO;

public class EletrodomesticoMapper {

	public static EletrodomesticoVO convertToVo(Eletrodomestico eletro) {
		
		return EletrodomesticoVO.builder()
								  .id(eletro.getId())
								  .tipoEletrodomesticoId(eletro.getTipoEletrodomestico().getId())
								  .tipoEletrodomesticoNome(eletro.getTipoEletrodomestico().getNome())
								  .nome(eletro.getNome())
								  .marca(eletro.getMarca())
								  .modelo(eletro.getModelo())
								  .voltagem(eletro.getVoltagem())
								  .potencia(eletro.getPotencia())
								  .build();
	}
	
	public static EletroUserVO convertToVo(EletroUser eletroUser) {
		
		return EletroUserVO.builder()
								  .id(eletroUser.getId())				  
								  .eletroId(eletroUser.getEletrodomestico().getId())
								  .tipoEletrodomesticoId(eletroUser.getEletrodomestico().getTipoEletrodomestico().getId())
								  .tipoEletrodomesticoNome(eletroUser.getEletrodomestico().getTipoEletrodomestico().getNome())
								  .nome(eletroUser.getEletrodomestico().getNome())
								  .marca(eletroUser.getEletrodomestico().getMarca())
								  .modelo(eletroUser.getEletrodomestico().getModelo())
								  .voltagem(eletroUser.getEletrodomestico().getVoltagem())
								  .potencia(eletroUser.getEletrodomestico().getPotencia())
								  .usuarioId(eletroUser.getUsuarioId())
								  .diaSemanaLigado(eletroUser.getDiaSemanaLigado())
								  .tempoDiarioLigado(eletroUser.getTempoDiarioLigado())
								  .build();
	}

	public static List<EletroUserVO> convertToVo(List<EletroUser> eletrosUser) {

		return eletrosUser.stream().map(EletroUserVO::new).collect(Collectors.toList());
	}
	
	public static List<EletroNomeVO> convertToNomeVo(List<String> eletroNomes) {

		return eletroNomes.stream().map(EletroNomeVO::new).collect(Collectors.toList());
	}
	
	public static List<EletroMarcaVO> convertToMarcaVo(List<String> eletroMarcas) {

		return eletroMarcas.stream().map(EletroMarcaVO::new).collect(Collectors.toList());
	}
	
	public static List<EletroModeloVO> convertToModeloVo(List<String> eletroModelos) {

		return eletroModelos.stream().map(EletroModeloVO::new).collect(Collectors.toList());
	}
	
	public static List<EletroVoltagemVO> convertToVoltagemVo(List<Integer> eletroVoltagens) {

		return eletroVoltagens.stream().map(EletroVoltagemVO::new).collect(Collectors.toList());
	}
}
