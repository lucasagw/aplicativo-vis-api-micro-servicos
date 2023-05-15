package api.vis.apigateway.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secret;

	@PostConstruct
	public void init() {

	}

	public Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(this.secret.getBytes()).parseClaimsJws(token.substring(7, token.length())).getBody();
	}

	private boolean isTokenExpired(String token) {
		if (this.getAllClaimsFromToken(token).getExpiration().getTime() > new Date().getTime()) {
			return false;
		}
		return true;
	}

	public boolean isInvalid(String token) {
		return this.isTokenExpired(token);
	}

	public boolean isTokenValid(String token) {
		return !isTokenExpired(token);
		/*
		 * try { Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token); return
		 * true; }catch (Exception e) { System.out.println("veio pro catch"); return
		 * false; }
		 */
	}

//	private boolean tokenExpired(String token) {
//		boolean expired = false;
//
//		try {
//			if (this.getAllClaimsFromToken(token).getExpiration().getTime() > new Date().getTime()) {
//				expired = false;
//			}
//
//		} catch (ExpiredJwtException e) {
//			response
//		}
//
//		return expired;
//	}

}
