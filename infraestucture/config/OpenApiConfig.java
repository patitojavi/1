package es.altia.bne.postulante.infraestucture.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI postulanteOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("BNE 2.0 - API Postulante")
                .version("1.0.0")
                .description("Microservicio que gestiona los datos del postulante, incluyendo CV, contacto y dirección.")
                .contact(new Contact()
                    .name("Equipo BNE Postulantes")
                    .email("soporte@bne.cl"))
                .license(new License()
                    .name("Propietary License")
                    .url("https://bne.cl/licencia"))
            )
            .servers(List.of(
                new Server().url("http://localhost:8105").description("Entorno local"),
                new Server().url("https://api-dev.bne.cl").description("Entorno DEV")
            ));
    }
}
