package api.vis.authorizationserver.util.jtw;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import api.vis.authorizationserver.feignclients.UsuarioFeignClient;
import api.vis.authorizationserver.model.dto.TokenDto;
import api.vis.authorizationserver.model.dto.UsuarioDetailsDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expirationDateInMs}")
	private int jwtExpirationInMs;
	
	@Value("${jwt.refreshExpirationDateInMs}")
	private int refreshExpirationDateInMs;

	@Autowired
	private UsuarioFeignClient usuarioFeignClient;
	
	public TokenDto generateToken(UserDetails userDetails) {
		
		Map<String, Object> claims = new HashMap<>();
		
		UsuarioDetailsDto usuario = usuarioFeignClient.findByTelefone(userDetails.getUsername());
		
		System.out.println(usuario);

		Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
		
		  claims.put("userId", usuario.getId());
	      claims.put("userName", usuario.getNome());
	      claims.put("roles", userDetails.getAuthorities());

		if (roles.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			claims.put("isAdmin", true);
		}
		if (roles.contains(new SimpleGrantedAuthority("ROLE_USER"))) {
			claims.put("isUser", true);
		}
		
		TokenDto token = new TokenDto(doGenerateToken(claims, userDetails.getUsername()), "Bearer",
				doGenerateRefreshToken(claims, userDetails.getUsername()));
		
		return token;
	}

	public String doGenerateToken(Map<String, Object> claims, String subject) {
		
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
				.setHeaderParam("typ", "JWT")
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();

	}
	
	public String doGenerateRefreshToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + refreshExpirationDateInMs))
				.setHeaderParam("typ", "JWT")
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();

	}

	public boolean validateToken(String authToken) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
			throw new BadCredentialsException("INVALID_CREDENTIALS", ex);
		} catch (ExpiredJwtException ex) {
			throw ex;
		}
	}
	
	public Long getUserId(String token) {
		
		Claims claims = Jwts.parser().setSigningKey(this.secret.getBytes()).parseClaimsJws(token).getBody();
		
		return Long.parseLong(claims.getSubject());
	}
	
	public UsuarioDetailsDto getUseFromToken(String token) {
		
		Claims claims = Jwts.parser().setSigningKey(this.secret.getBytes()).parseClaimsJws(token).getBody();
		
		UsuarioDetailsDto usuario = usuarioFeignClient.findByTelefone(claims.getSubject());
		
		return usuario;
	}

	public Claims getClaimsFromToken(String token) {
		 Claims claims = Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		 return claims;
	}

	public String getUsernameFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		return claims.getSubject();

	}

	public List<SimpleGrantedAuthority> getRolesFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();

		List<SimpleGrantedAuthority> roles = null;

		Boolean isAdmin = claims.get("isAdmin", Boolean.class);
		Boolean isUser = claims.get("isUser", Boolean.class);

		if (isAdmin != null && isAdmin) {
			roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}

		if (isUser != null && isAdmin) {
			roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
		}
		return roles;

	}

}
