package api.vis.authorizationserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.vis.authorizationserver.model.dto.TokenDto;
import api.vis.authorizationserver.model.form.LoginForm;
import api.vis.authorizationserver.service.CustomUserDetailsService;
import api.vis.authorizationserver.util.jtw.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping(value = "/auth")
@Validated
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping(value = "/login")
	public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody LoginForm authenticationRequest) throws Exception {
		try {
				
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getTelefone(), authenticationRequest.getSenha()));	
		} 
		catch (DisabledException e) {
			
			throw new Exception("USER_DISABLED", e);
		}
		catch (BadCredentialsException e) {
			
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		UserDetails userdetails = userDetailsService.loadUserByUsername(authenticationRequest.getTelefone());
		
		TokenDto token = jwtUtil.generateToken(userdetails);
		
		System.out.println(token);
		
		return ResponseEntity.ok(token);
	}
	
	@GetMapping(value = "/refreshtoken/{oldtoken}")
	public ResponseEntity<?> refreshtoken(@NotBlank @PathVariable String oldtoken ){
				
		Claims claims = jwtUtil.getClaimsFromToken(oldtoken);
		
		String refreshToken = jwtUtil.doGenerateRefreshToken(claims, claims.getSubject());
		
		String token = jwtUtil.doGenerateToken(claims, claims.getSubject());
		
		return ResponseEntity.ok(new TokenDto(token, "Bearer", refreshToken));
	}
	
	@GetMapping(value = "/checktoken/{token}")
	public ResponseEntity<?> checkToken(@NotBlank @PathVariable String token) {
		
		return ResponseEntity.ok(jwtUtil.validateToken(token));
	}
	
	@GetMapping(value = "/usuario/token/{token}")
	public ResponseEntity<?> obterUsuarioPorToken(@NotBlank @PathVariable String token) {
	
		return ResponseEntity.ok(jwtUtil.getUseFromToken(token));
	}
}