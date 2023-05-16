package api.vis.consumo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import api.vis.consumo.feignclient.BandeiraTarifariaFeignclient;
import api.vis.consumo.model.BandeiraTarifaria;
import api.vis.consumo.model.form.BandeiraTarifariaForm;
import api.vis.consumo.model.form.Item;
import api.vis.consumo.repository.BandeiraRepository;
import api.vis.consumo.repository.BandeiraTarifariaRepository;
import api.vis.util.VisAquaeConstantes.Key;

@Component
@Validated
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final String TIME_ZONE = "America/Sao_Paulo";

	@Autowired
	private BandeiraRepository bandeiraRepository;

	@Autowired
	private BandeiraTarifariaRepository bandeiraTarifaRepository;

	@Autowired
	private BandeiraTarifariaFeignclient bandeiraTarifariaFeignclient;

	//@Scheduled(cron = "0 0 0 1 * ?", zone = TIME_ZONE) // Executa no dia 01 de cada mês
	//@Scheduled(fixedDelay  = 60000 * 1) // Executa no dia 01 de cada mês
	public void checkBandeiraTarifariaFeignclient() {

		try {

			salvarTarifaMesAtual(bandeiraTarifariaFeignclient.getBandeiras(Key.API_SETOR_ELETRICO));

			log.info("BANDEIRA TARIFARIA DO MÊS CORRENTE FOI SALVA COM SUCESSO!");

		} catch (Exception e) {

			e.printStackTrace();

			String mensagem = e.getMessage() != null ? e.getMessage() : "Falha na consulta da tarifa";

			log.error(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private void salvarTarifaMesAtual(BandeiraTarifariaForm form) {

		System.out.println(form.getTotal());
		
		for (Item item : form.getItems()) {
          
			System.out.println("aqui: " + item);
			
			System.out.println("aqui: " + form.getItems());
			
			bandeiraTarifaRepository.save(new BandeiraTarifaria(bandeiraRepository.findByflagType(item.getFlagType()),
					item.getMonth(), item.getValue()));
		}
	}

}
