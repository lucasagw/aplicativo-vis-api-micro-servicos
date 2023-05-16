package api.vis.consumo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.vis.consumo.model.response.BandeiraTarifariaVO;
import api.vis.consumo.model.response.ConsumoEnergiaMetricaVO;
import api.vis.consumo.model.response.ConsumoEnergiaVO;
import api.vis.consumo.service.ConsumoEnergiaService;
import api.vis.util.UtilService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/consumo_energia")
@Validated
public class ConsumoEnergiaController {

	@Autowired
	private ConsumoEnergiaService service;
	
	@Autowired
	private UtilService utilService;

	@GetMapping("/diario/eletro/{eletroId}/usuario/{userId}")
	@Operation(summary = "Obtem consumo diário de um único Eletroméstico pelo id", description = "Retorna alguns elementos de ConsumoEnergiaVO")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	@ApiResponse(responseCode = "401", description = "Unauthorized")
	@ApiResponse(responseCode = "404", description = "Not Found")
	@ApiResponse(responseCode = "500", description = "Internal Server Error")
	public ResponseEntity<ConsumoEnergiaVO> calcularConsumoDiario(@Positive @PathVariable Long eletroId, @Positive @PathVariable Long userId) {

		return ResponseEntity.ok(service.calcularConsumoDiario(eletroId, userId));
	}

	@GetMapping("/mensal/eletro/{eletroId}/usuario/{userId}")
	@Operation(summary = "Obtem consumo mensal de um único Eletroméstico pelo id", description = "Retorna alguns elementos de ConsumoEnergiaVO")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	@ApiResponse(responseCode = "401", description = "Unauthorized")
	@ApiResponse(responseCode = "404", description = "Not Found")
	@ApiResponse(responseCode = "500", description = "Internal Server Error")
	public ResponseEntity<ConsumoEnergiaVO> calcularConsumoMensal(@Positive @PathVariable Long eletroId, @Positive @PathVariable Long userId) {

		return ResponseEntity.ok(service.calcularConsumoMensal(eletroId, userId));
	}

	@GetMapping("/customediomensal/eletro/{eletroId}/usuario/{userId}")
	@Operation(summary = "Obtem custo médio mensal de um único Eletroméstico pelo id", description = "Retorna alguns elementos de ConsumoEnergiaVO")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	@ApiResponse(responseCode = "401", description = "Unauthorized")
	@ApiResponse(responseCode = "404", description = "Not Found")
	@ApiResponse(responseCode = "500", description = "Internal Server Error")
	public ResponseEntity<ConsumoEnergiaVO> calcularCustoMedioMensal(@Positive @PathVariable Long eletroId, @Positive @PathVariable Long userId) {

		return ResponseEntity.ok(service.calcularCustoMedioMensal(eletroId, userId));
	}

	@GetMapping("/metrica/eletro/{eletroId}/usuario/{userId}")
	@Operation(summary = "Calcular média métrica de um único Eletroméstico víncuado ao Usuário Logado", description = "Retorna alguns elementos de ConsumoEnergiaVO")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	@ApiResponse(responseCode = "401", description = "Unauthorized")
	@ApiResponse(responseCode = "404", description = "Not Found")
	@ApiResponse(responseCode = "500", description = "Internal Server Error")
	public ResponseEntity<ConsumoEnergiaVO> calcularMetricaEletroUser(@Positive @PathVariable Long eletroId, @Positive @PathVariable Long userId) {

		return ResponseEntity.ok(service.calcularMetricaEletroUser(eletroId, userId));
	}
	
	@GetMapping("/metrica/usuario/{id}")
	@Operation(summary = "Obtem média métrica de um ou mais Eletromésticos vínculados ao Usuário Logado", description = "Retorna elementos de ConsumoEnergiaMetricaVO")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	@ApiResponse(responseCode = "401", description = "Unauthorized")
	@ApiResponse(responseCode = "404", description = "Not Found")
	@ApiResponse(responseCode = "500", description = "Internal Server Error")
	public ResponseEntity<List<ConsumoEnergiaMetricaVO>> getMetricaUser(@Positive @PathVariable Long id) {

		return ResponseEntity.ok(service.getMetricaUser(id));
	}
	
	@GetMapping("/bandeira")
	@Operation(summary = "Obtem Bandeira Tarifaria pelo mês corrente", description = "Retorna elementos de BandeiraTarifaria")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	@ApiResponse(responseCode = "401", description = "Unauthorized")
	@ApiResponse(responseCode = "500", description = "Internal Server Error")
	public ResponseEntity<BandeiraTarifariaVO> findBandeiraTarifariaByMonth() {

		return ResponseEntity.ok(utilService.findBandeiraTarifariaByMonth());
	}
}
