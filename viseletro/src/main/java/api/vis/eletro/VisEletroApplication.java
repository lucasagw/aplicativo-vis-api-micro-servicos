package api.vis.eletro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class VisEletroApplication {

	public static void main(String[] args) {
		SpringApplication.run(VisEletroApplication.class, args);
	}
}
