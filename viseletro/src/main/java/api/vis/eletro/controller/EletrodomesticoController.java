package api.vis.eletro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.vis.eletrodomestico.model.form.AssociarEletroUserForm;
import api.vis.eletrodomestico.model.form.EletrodomesticoForm;
import api.vis.eletrodomestico.service.EletrodomesticoService;
import api.vis.util.VisAquaeConstantes.Deleted;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/eletro")
@Validated
public class EletrodomesticoController {

	@Autowired
	private EletrodomesticoService service;

	@GetMapping("/{id}")
	@Operation(summary = "Obtem um único Eletroméstico pelo id", description = "Retorna Eletrodomestico")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	@ApiResponse(responseCode = "401", description = "Unauthorized")
	@ApiResponse(responseCode = "404", description = "Not Found")
	@ApiResponse(responseCode = "500", description = "Internal Server Error")
	public ResponseEntity<?> findById(@Positive @PathVariable Long id) {

		return ResponseEntity.ok(service.findById(id));
	}
	
	@GetMapping("/usuario/{id}")
	@Operation(summary = "Obtem Eletroméstico pelo id do Usuário Logado", description = "Retorna Eletrodomestico")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	@ApiResponse(responseCode = "401", description = "Unauthorized")
	@ApiResponse(responseCode = "404", description = "Not Found")
	@ApiResponse(responseCode = "500", description = "Internal Server Error")
	public ResponseEntity<?> findByUsuarioId(@Positive @PathVariable Long id) {

		return ResponseEntity.ok(service.findByUsuarioId(id));
	}
	
	@GetMapping("/tipos")
	@Operation(summary = "Obtem todos os tipos de eletrodomésticos", description = "Retorna Eletrodomestico")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	@ApiResponse(responseCode = "401", description = "Unauthorized")
	@ApiResponse(responseCode = "500", description = "Internal Server Error")
	public ResponseEntity<?> findAllTipoEletrodomestico() {

		return ResponseEntity.ok(service.findAllTipoEletrodomestico());
	}
	
	@GetMapping("/tipo/{id}")
	@Operation(summary = "Obtem todos os nomes de eletrodomésticos salvos na base, baseado no tipos de eletrodoméstico", description = "Retorna Eletrodomestico")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	@ApiResponse(responseCode = "401", description = "Unauthorized")
	@ApiResponse(responseCode = "500", description = "Internal Server Error")
	public ResponseEntity<?> findAllNomeByTipoEletrodomestico(@Positive @PathVariable Long id) {

		return ResponseEntity.ok(service.findAllNomeByTipoEletrodomestico(id));
	}
	
	@GetMapping("/marcas/{nome}")
	@Operation(summary = "Obtem todos as marcas de eletrodomésticos salvos na base, baseado no nome do Eletro", description = "Retorna Eletrodomestico")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	@ApiResponse(responseCode = "401", description = "Unauthorized")
	@ApiResponse(responseCode = "500", description = "Internal Server Error")
	public ResponseEntity<?> findAllMarcaByNomeEletro(@NotBlank @PathVariable String nome) {

		return ResponseEntity.ok(service.findAllMarcaByNomeEletro(nome));
	}
	
	@GetMapping("/modelos/{marca}/nome/{nome}")
	@Operation(summary = "Obtem todos os nomes de eletrodomésticos salvos na base", description = "Retorna Eletrodomestico")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	@ApiResponse(responseCode = "401", description = "Unauthorized")
	@ApiResponse(responseCode = "500", description = "Internal Server Error")
	public ResponseEntity<?> findAllModeloByMarcaAndNomeEletro(@NotBlank @PathVariable String marca, @NotBlank @PathVariable String nome) {

		return ResponseEntity.ok(service.findAllModeloByMarcaAndNomeEletro(marca, nome));
	}
	
	@GetMapping("/voltagens/{modelo}")
	@Operation(summary = "Obtem todos os nomes de eletrodomésticos salvos na base", description = "Retorna Eletrodomestico")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	@ApiResponse(responseCode = "401", description = "Unauthorized")
	@ApiResponse(responseCode = "500", description = "Internal Server Error")
	public ResponseEntity<?> findAllVoltagemByModelo(@NotBlank @PathVariable String modelo) {

		return ResponseEntity.ok(service.findAllVoltagemByModelo(modelo));
	}
	
	@PostMapping("/associar/user")
	@Operation(summary = "Associa um elemento da tabela Eletroméstico com o usuário logado, baseado nas informações passadas pelo AssociarEletroUserForm")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	@ApiResponse(responseCode = "401", description = "Unauthorized")
	@ApiResponse(responseCode = "500", description = "Internal Server Error")
	public ResponseEntity<Void> associarEletroUser(@Valid @RequestBody AssociarEletroUserForm form) {

		service.associarEletroUser(form);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PostMapping("/aparelho")
	@Operation(summary = "Cria um elemento na tabela Eletroméstico com as informações passadas pelo EletrodomesticoForm")
	@ApiResponse(responseCode = "201", description = "Created")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	@ApiResponse(responseCode = "401", description = "Unauthorized")
	@ApiResponse(responseCode = "500", description = "Internal Server Error")
	public ResponseEntity<?> criarEletrodomestico(@Valid @RequestBody EletrodomesticoForm form) {

		return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(form));
	}

	@PutMapping("/aparelho/{id}")
	@Operation(summary = "Edita um elemento na tabela Eletroméstico com as informações passadas pelo EletrodomesticoForm")
	@ApiResponse(responseCode = "201", description = "Created")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	@ApiResponse(responseCode = "401", description = "Unauthorized")
	@ApiResponse(responseCode = "404", description = "Not Found")
	@ApiResponse(responseCode = "500", description = "Internal Server Error")
	public ResponseEntity<?> editarEletrodomestico(@Positive @PathVariable Long id, @Valid @RequestBody EletrodomesticoForm form) {

		return ResponseEntity.ok(service.editar(form, id));
	}

	@DeleteMapping("/aparelho/{eletroId}/usuario/{userId}")
	@Operation(summary = "Apaga um elemento na tabela Eletroméstico vínculado ao id passado pela url (@PathVariable)")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	@ApiResponse(responseCode = "401", description = "Unauthorized")
	@ApiResponse(responseCode = "404", description = "Not Found")
	@ApiResponse(responseCode = "500", description = "Internal Server Error")
	public ResponseEntity<?> excluirAssociacaoEletrodomestico(@Positive @PathVariable Long eletroId, @Positive @PathVariable Long userId) {

		service.excluir(eletroId, userId);

		return ResponseEntity.status(HttpStatus.OK).body(Deleted.DELETED);
	}
	
	@DeleteMapping("/aparelho/{id}")
	@Operation(summary = "Apaga um elemento na tabela Eletroméstico vínculado ao id passado pela url (@PathVariable)")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	@ApiResponse(responseCode = "401", description = "Unauthorized")
	@ApiResponse(responseCode = "404", description = "Not Found")
	@ApiResponse(responseCode = "500", description = "Internal Server Error")
	public ResponseEntity<?> excluirCadastroEletrodomestico(@Positive @PathVariable Long id) {

		service.excluir(id);

		return ResponseEntity.status(HttpStatus.OK).body(Deleted.DELETED);
	}

}
