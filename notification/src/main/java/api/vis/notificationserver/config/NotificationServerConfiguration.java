package api.vis.notificationserver.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableFeignClients(basePackages = {

})

public class NotificationServerConfiguration {
	
	
}
