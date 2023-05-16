package api.vis.erro;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@Component
@RestControllerAdvice
public class Erro implements Serializable {

	private static final long serialVersionUID = 1L;

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, Object> handleValidationExceptions(MethodArgumentNotValidException ex) {

		Map<String, Object> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
			errors.put("código", HttpStatus.BAD_REQUEST.value());
		});
		return errors;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public Map<String, Object> handleValidationExceptions(ConstraintViolationException ex) {

		Map<String, Object> errors = new HashMap<>();
		ex.getConstraintViolations().forEach((error) -> {
			errors.put("mensagem", error.getMessage());
			errors.put("código", HttpStatus.BAD_REQUEST.value());
		});
		return errors;
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoSuchElementException.class)
	public Map<String, Object> handleValidationNoSuchElementException(NoSuchElementException ex) {

		Map<String, Object> errors = new HashMap<>();
		errors.put("error", "Não foi encontrado " + ex.getMessage());
		errors.put("código", HttpStatus.NOT_FOUND.value());
		return errors;
	}

	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	@ExceptionHandler(NumberFormatException.class)
	public Map<String, Object> handleValueNumberFormatException(NumberFormatException ex) {

		Map<String, Object> errors = new HashMap<>();
		errors.put("error", "Formato de dado " + ex.getMessage() + " inválido!");
		errors.put("código", HttpStatus.NOT_FOUND.value());
		return errors;
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public Map<String, Object> handleValidationInternalException(Exception ex) {

		Map<String, Object> errors = new HashMap<>();
		errors.put("error", "Falha no endpoint: " + ex.getMessage());
		errors.put("código", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return errors;
	}
}
