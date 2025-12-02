package es.altia.bne.postulante.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // Desactivar CSRF (si no, los POST desde Swagger dan 403 fácilmente)
            .csrf(AbstractHttpConfigurer::disable)

            // TODO permitido
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            )

            // Para que H2 funcione en iframe y el CSP no moleste con Swagger
            .headers(headers -> headers
                .frameOptions(frame -> frame.sameOrigin())
                .contentSecurityPolicy(csp ->
                    csp.policyDirectives("script-src 'self' 'unsafe-inline'; object-src 'self'")
                )
            );

        return http.build();
    }
}
