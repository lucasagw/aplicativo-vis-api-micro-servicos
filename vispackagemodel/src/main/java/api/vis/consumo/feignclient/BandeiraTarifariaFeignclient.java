package api.vis.consumo.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import api.vis.consumo.model.form.BandeiraTarifariaForm;
import api.vis.erro.LoadBalancerConfiguration;

@Component
@FeignClient(name = "BandeiraTarifariaClient", url = "https://apise.way2.com.br", path = "/v1", configuration = LoadBalancerConfiguration.class)
public interface BandeiraTarifariaFeignclient {

	
	@GetMapping(value = "/bandeiras")
	public BandeiraTarifariaForm getBandeiras(@RequestParam("apikey") String apikey);

}
