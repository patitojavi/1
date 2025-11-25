package es.altia.bne.postulante.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http

            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .anyRequest().permitAll()
            )

            .csrf(csrf -> csrf.disable())

            .headers(headers -> headers
                .frameOptions(frame -> frame.sameOrigin())

                .contentSecurityPolicy(csp -> csp
                    .policyDirectives("script-src 'self' 'unsafe-inline'; object-src 'self'")
                )
            );

        return http.build();
    }
}
