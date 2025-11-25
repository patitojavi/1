package es.altia.bne.postulante;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

import es.altia.bne.postulante.infraestucture.config.PostulanteMicroserviceBootstrap;

@SpringBootApplication
@ComponentScan(basePackageClasses = PostulanteMicroserviceBootstrap.class)
public final class PostulanteMicroserviceApplication {

    private PostulanteMicroserviceApplication() {
    }

    public static void main(String[] args) {
        configureApplication(new SpringApplicationBuilder()).run(args);
    }

    private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
        return builder.sources(PostulanteMicroserviceApplication.class).bannerMode(Banner.Mode.OFF);
    }
}
