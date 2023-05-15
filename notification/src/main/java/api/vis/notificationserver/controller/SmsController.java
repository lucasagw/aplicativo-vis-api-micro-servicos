package api.vis.notificationserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.vis.notificationserver.feignclient.SmsFeignClient;
import api.vis.notificationserver.model.form.SmsForm;
import api.vis.notificationserver.model.response.SmsVO;

@RestController
@RequestMapping("/sms")
public class SmsController {
	
	@Autowired
	private SmsFeignClient smsFeignClient;
	
	@PostMapping
	public SmsVO enviarSms(@RequestParam("telefone") String telefone, @RequestParam("codigo") int codigo){

		return smsFeignClient.smsSend(new SmsForm(telefone, codigo));
	}
}
