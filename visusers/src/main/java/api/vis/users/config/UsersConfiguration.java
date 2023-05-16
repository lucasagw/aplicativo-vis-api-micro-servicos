package api.vis.users.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {
 "api.vis.user.repository",
})
@EntityScan(basePackages = {
 "api.vis.user.model",

})
@ComponentScan(basePackages = {
 "api.vis.user.service",
 "api.vis.erro",
 "api.vis.swagger"
})
@EnableFeignClients(basePackages = {
 "api.vis.user.feignclient",
 "api.vis.notificationserver.feignclient",
})
public class UsersConfiguration {
	
}
