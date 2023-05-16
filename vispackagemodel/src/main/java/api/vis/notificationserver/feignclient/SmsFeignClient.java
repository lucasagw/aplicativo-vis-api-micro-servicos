package api.vis.notificationserver.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import api.vis.erro.LoadBalancerConfiguration;
import api.vis.notificationserver.model.form.SmsForm;
import api.vis.notificationserver.model.response.SmsVO;


@Component
@FeignClient(value = "vis-api-notify", url = "https://api.smstoken.com.br" , configuration = LoadBalancerConfiguration.class)
public interface SmsFeignClient {
	
	//@PostMapping("/token/v1/verify")
	@RequestMapping(method = RequestMethod.POST,
	value = "/token/v1/verify",
	produces = MediaType.APPLICATION_JSON_VALUE)
	public SmsVO smsSend(SmsForm form);

}
