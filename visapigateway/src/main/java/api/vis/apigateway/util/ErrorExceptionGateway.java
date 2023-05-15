package api.vis.apigateway.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.ExpiredJwtException;

@Component
@RestControllerAdvice
public class ErrorExceptionGateway implements Serializable{
	private static final long serialVersionUID = 1L;

	@ResponseStatus(HttpStatus.UNAUTHORIZED) 
	@ExceptionHandler(ExpiredJwtException.class)
	public Map<String, Object> ExceptionHandlingWebHandler(){
	    
		String mensagem = "Token Expirado!";
		Map<String, Object> errors = new HashMap<>();;
		errors.put("error", mensagem);
		errors.put("código", HttpStatus.UNAUTHORIZED.value());
	    return errors;
	}
}