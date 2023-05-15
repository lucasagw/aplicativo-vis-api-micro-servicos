package api.vis.authorizationserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AuthorizationserverApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(AuthorizationserverApplication.class, args);
	}
}
