package api.vis.erro;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

@Configuration
public class LoadBalancerConfiguration {
	
	@Bean
	@LoadBalanced
	Logger.Level feignLoggerLevel(){ return Logger.Level.FULL;}	   

}
