package es.altia.bne.postulante.infraestucture.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import es.altia.bne.common.rest.bootstrap.CommonRestControllerBootstrap;

@ComponentScan(basePackages = {"es.altia.bne.postulante.api.controller"})
@Configuration
public class PostulanteMicroserviceRestControllerBootstrap extends CommonRestControllerBootstrap {

}
