package api.vis.authorizationserver.util.error;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import feign.FeignException;

@RestControllerAdvice
public class ErroRestExceptionHandler extends ResponseEntityExceptionHandler {
	
	   protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	        List<ErrorObject> errors = getErrors(ex);
	        ErrorResponse errorResponse = getErrorResponse(ex, status, errors);
	        return new ResponseEntity<>(errorResponse, status);
	    }
	   
	   //@ExceptionHandler(MethodArgumentNotValidException.class)
	    private ErrorResponse getErrorResponse(MethodArgumentNotValidException ex, HttpStatus status, List<ErrorObject> errors) {
	        return new ErrorResponse("Requisição possui campos inválidos", status.value(),
	                status.getReasonPhrase(), ex.getBindingResult().getObjectName(), errors);
	    }

	    private List<ErrorObject> getErrors(MethodArgumentNotValidException ex) {
	        return ex.getBindingResult().getFieldErrors().stream()
	                .map(error -> new ErrorObject(error.getDefaultMessage(), error.getField(), error.getRejectedValue()))
	                .collect(Collectors.toList());
	    }
	    
		@ResponseStatus(HttpStatus.NOT_FOUND) 
		@ExceptionHandler(FeignException.BadRequest.class)
		public Map<String, Object> handleFeignStatusExceptionNotFound(Exception ex){
		
			String mensagem = "Telefone incorreto ou cpf incorreto ou usuário inexistente!";
			Map<String, Object> errors = new HashMap<>();
			errors.put("error",mensagem);
			errors.put("código", HttpStatus.NOT_FOUND.value());
		    return errors;
		}
		
		@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) 
		@ExceptionHandler(FeignException.InternalServerError.class)
		public Map<String, Object> handleFeignStatusException(Exception ex){
			String mensagem = "Telefone incorreto ou cpf incorreto ou usuário inexistente!";
			Map<String, Object> errors = new HashMap<>();
			errors.put("error",mensagem);
			errors.put("código", HttpStatus.INTERNAL_SERVER_ERROR.value());
		    return errors;
		}
		
		@ResponseStatus(HttpStatus.NOT_FOUND) 
		@ExceptionHandler(FeignException.NotFound.class)
		public Map<String, Object> handleFeignStatusException1(FeignException.NotFound ex){
		    
			String mensagem = "Telefone incorreto ou cpf incorreto ou usuário inexistente!";
			Map<String, Object> errors = new HashMap<>();;
			errors.put("error", mensagem);
			errors.put("código", HttpStatus.NOT_FOUND.value());
		    return errors;
		}
		
		@ResponseStatus(HttpStatus.FORBIDDEN) 
		@ExceptionHandler(AuthenticationException.class)
		public Map<String, Object> handleFeignStatusException2(){
		    
			String mensagem = "Senha Incorreta!";
			Map<String, Object> errors = new HashMap<>();;
			errors.put("error", mensagem);
			errors.put("código", HttpStatus.FORBIDDEN.value());
		    return errors;
		}
}
