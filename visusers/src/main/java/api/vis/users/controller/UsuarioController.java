package api.vis.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.vis.user.model.Usuario;
import api.vis.user.model.form.ActiveAccountForm;
import api.vis.user.model.form.UsuarioForm;
import api.vis.user.model.response.UsuarioDetailsVO;
import api.vis.user.model.response.UsuarioVO;
import api.vis.user.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@RestController
@RequestMapping(value = "/usuario")
@Validated
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	
	@GetMapping("/{id}")
	@Operation(summary = "Faz uma busca que retorna um usuário pelo seu id")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	@ApiResponse(responseCode = "401", description = "Unauthorized")
	@ApiResponse(responseCode = "404", description = "Not Found")
	@ApiResponse(responseCode = "500", description = "Internal Server Error")
	public ResponseEntity<Usuario> findById(@Positive @PathVariable  Long id) {				

		return ResponseEntity.ok(usuarioService.findById(id));	
	}
	
	@GetMapping(value = "/telefone/{telefone}")
	@Operation(summary = "obtem um único usuário pelo número telefônico", description = "retorna usuário no formato UsuarioDetailsVO, que contém informaçoes de cadastro e código de verificação")
	@ApiResponse(responseCode = "200", description = "Success")
	@ApiResponse(responseCode = "404", description = "Not found")
	public ResponseEntity<UsuarioDetailsVO> findByTelefone(@Size(min = 11, max = 13, message = "O telefone está incorreto!") @PathVariable String telefone) {
		
		return ResponseEntity.ok(new UsuarioDetailsVO(usuarioService.findByTelefone(telefone)));
	}

	@GetMapping(value = "/telefonelogin/{telefone}")
	@Operation(summary = "Obtem um único usuário pelo número telefônico", description = "Retorna usuário no formato UsuarioVO")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	@ApiResponse(responseCode = "401", description = "Unauthorized")
	@ApiResponse(responseCode = "404", description = "Not Found")
	@ApiResponse(responseCode = "500", description = "Internal Server Error")
	public ResponseEntity<UsuarioVO> findLoginByTelefone(@Size(min = 11, max = 13) @PathVariable String telefone) {

		return ResponseEntity.ok(new UsuarioVO(usuarioService.findByTelefone(telefone)));
	}

	@PostMapping(value = "/createaccount")
	@Operation(summary = "Cria uma elemento na tabela Usuario com as informações passadas pelo UsuarioForm")
	@ApiResponse(responseCode = "201", description = "Created")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	@ApiResponse(responseCode = "500", description = "Internal Server Error")
	public ResponseEntity<?> register(@Valid @RequestBody UsuarioForm form) {

		return usuarioService.register(form);
	}

	@PostMapping("/activeaccount")
	@Operation(summary = "Ao receber o ActiveAccountForm com código de validação correto modifica o flagAtivo de usuário para verdadeiro, exclue o item da tabela validação e cria conexão com tabela pontuação")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "401", description = "Unauthorized")
	@ApiResponse(responseCode = "404", description = "Not found")
	@ApiResponse(responseCode = "500", description = "Internal Server Error")
	public ResponseEntity<Boolean> activeAccount(@RequestBody ActiveAccountForm form) {

		Usuario usuario = usuarioService.findByTelefone(form.getTelefone());

		if (usuario.getVerificacao() != null && usuario.getVerificacao().getCodigo().equals(form.getCodigo())) {

			usuarioService.activeUser(usuario.getId());

			return new ResponseEntity<>(true, HttpStatus.OK);
		}
		return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
	}
}