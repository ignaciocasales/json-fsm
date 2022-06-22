package ignaciocasales.jsonfsm.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "JSON FSM SERVICE",
                description = "This project was developed for the subject of Discrete Mathematics at the Universidad Atlántida Argentina." +
                        "Check out the code at: https://github.com/ignaciocasales/json-fsm",
                version = "v1",
                license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"))
)
public class OpenApiConfig {
}
