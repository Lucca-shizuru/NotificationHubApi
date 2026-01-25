package lucca.shizuru.notifyhub.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("NotifyHub API")
                        .version("1.0")
                        .description("Sistema centralizado para agendamento e envio de notificações via E-mail e SMS.")
                        .contact(new Contact()
                                .name("Lucca")
                                .email("shizurulucca.dev@gmail.com")));
    }
}
