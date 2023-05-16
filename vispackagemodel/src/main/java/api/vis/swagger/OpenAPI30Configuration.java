package api.vis.swagger;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
@EnableWebMvc
public class OpenAPI30Configuration extends WebMvcConfigurationSupport {

	@Bean
	public OpenAPI customizeOpenAPI() {
		final String securitySchemeName = "bearerAuth";
		return new OpenAPI()
							.addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
							.components(new Components()
							.addSecuritySchemes(securitySchemeName, new SecurityScheme()
							.name(securitySchemeName)
							.type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
							.info(new Info().title("API VIS DOCUMENTATION").version("1.0.0"));
	}

	@Bean
	public GroupedOpenApi api() {
		String[] paths = { "/visApiUser/**", "/visApiEletro/**", "/visApiConsumo/**" };
		return GroupedOpenApi.builder().group("api").pathsToMatch(paths).build();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/swagger-ui/**")
	            .addResourceLocations("classpath:/META-INF/resources/webjars/springdoc-openapi-ui/")
	            .addResourceLocations("classpath:/META-INF/resources/webjars/swagger-ui/4.10.3/swagger-initializer.js")
	            .addResourceLocations("classpath:/META-INF/resources/webjars/swagger-ui/4.10.3/index.html")
	            .addResourceLocations("classpath:/META-INF/resources/webjars/swagger-ui/4.10.3/swagger-ui.css")
	            .addResourceLocations("classpath:/META-INF/resources/webjars/swagger-ui/4.10.3/index.css")
	            .addResourceLocations("classpath:/META-INF/resources/webjars/swagger-ui/4.10.3/swagger-ui-bundle.js")
	            .addResourceLocations("classpath:/META-INF/resources/webjars/swagger-ui/4.10.3/swagger-ui-standalone-preset.js");
	           // .resourceChain(false);
	}

}