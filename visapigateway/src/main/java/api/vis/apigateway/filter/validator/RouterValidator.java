package api.vis.apigateway.filter.validator;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouterValidator {
	
	public static final List<String> openApiEndpoints= List.of(
            "/visAuthorizationServer/auth/login",
            "/visApiUser/usuario/create",
            "/visApiUser/usuario/active"
            
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> {
                    	return request.getURI().getPath().contains(uri);
                    });

}
