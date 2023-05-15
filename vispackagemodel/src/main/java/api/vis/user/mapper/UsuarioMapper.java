package api.vis.user.mapper;

import java.util.List;
import java.util.stream.Collectors;

import api.vis.user.model.Usuario;
import api.vis.user.model.response.UsuarioVO;

public class UsuarioMapper {

	public static List<UsuarioVO> convertToVO(List<Usuario> usuarios) {

		return usuarios.stream().map(UsuarioVO::new).collect(Collectors.toList());
	}
	
}
