package api.vis.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication 
public class VisUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(VisUsersApplication.class, args);
	}
}
