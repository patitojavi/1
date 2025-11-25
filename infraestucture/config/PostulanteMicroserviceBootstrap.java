package es.altia.bne.postulante.infraestucture.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import es.altia.bne.common.bootstrap.CommonConfigBootstrap;

@EnableCaching
@Configuration
@Import({PostulanteMicroserviceRestControllerBootstrap.class, CommonConfigBootstrap.class})
@ComponentScan(basePackages = {"es.altia.bne.postulante.infraestucture.persistence.repository", "es.altia.bne.postulante.application.service.impl",
        "es.altia.bne.postulante.application.mapper"})
public class PostulanteMicroserviceBootstrap {

}
