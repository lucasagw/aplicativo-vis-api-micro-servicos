package api.vis.authorizationserver.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EntityScan(basePackages = {

})
@ComponentScan(basePackages = {

})
@EnableFeignClients(basePackages = {

})
public class AuthorizationserverConfiguration {
	
}


