package api.vis.eletro.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {
"api.vis.eletrodomestico.repository",
})
@EntityScan(basePackages = {
"api.vis.eletrodomestico.model",
"api.vis.user.model"
})
@ComponentScan(basePackages = {
"api.vis.eletrodomestico.service",
"api.vis.erro",
"api.vis.swagger"
})
@EnableFeignClients(basePackages = {

})
public class EletroConfiguration {

	
}
