package api.vis.authorizationserver.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import api.vis.authorizationserver.model.UsuarioDto;
import api.vis.authorizationserver.model.dto.UsuarioDetailsDto;

@Component
@FeignClient(name = "vis-api-user", url = "http://localhost:8764/visApiUser/usuario")//http://visusers-api:8764/visApiUser/usuario
public interface UsuarioFeignClient {

	@GetMapping(value= "/telefonelogin/{telefone}")
	public UsuarioDto findLoginByTelefone(@PathVariable String telefone);
	
	@GetMapping(value= "/telefone/{telefone}")
	public UsuarioDetailsDto findByTelefone(@PathVariable String telefone);
	
}
