package api.vis.authorizationserver.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import api.vis.authorizationserver.feignclients.UsuarioFeignClient;
import api.vis.authorizationserver.model.UsuarioDto;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	
	@Autowired
	private UsuarioFeignClient usuarioFeignClient;
	
//	@Override
//	public UserDetails loadUserByUsername(String telefone) throws UsernameNotFoundException {
//		UsuarioDto usuario = usuarioFeignClient.findLoginByTelefone(telefone);
//		if (usuario != null) {
//			
//			List<PerfilDto> roles = new ArrayList<>();
//			roles.add(new PerfilDto("ROLE_ADMIN")); 
//			usuario.setRoles(roles);
//			return usuario;
//		}
//		
//		if(usuario.isFlagAtivo() == false) {
//			return (UserDetails) new UsernameNotFoundException("Usuario não está ativo");
//		}
//		return (UserDetails) new UsernameNotFoundException("Dados Invalidos");
//	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<SimpleGrantedAuthority> roles = null;
			
		UsuarioDto user = usuarioFeignClient.findLoginByTelefone(username);
		
		System.out.println("user:" + user);
		
		if (user != null) {
			
			user.setRole("ROLE_ADMIN");
	
			roles = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
			
			return new User(user.getTelefone(), user.getSenha(), roles);
		}
		throw new UsernameNotFoundException("User not found with the name " + username);		
	}
}