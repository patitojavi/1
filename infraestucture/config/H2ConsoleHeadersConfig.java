package es.altia.bne.postulante.infraestucture.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Relaja el CSP y X-Frame-Options solo para /h2-console cuando el perfil h2 está activo.
 */
@Configuration
@Profile("h2")
public class H2ConsoleHeadersConfig {

  @Bean
  public FilterRegistrationBean<OncePerRequestFilter> h2CspRelax() {
    OncePerRequestFilter filter = new OncePerRequestFilter() {
      @Override
      protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
          throws ServletException, IOException {
        if (req.getRequestURI() != null && req.getRequestURI().startsWith("/h2-console")) {
          res.setHeader("Content-Security-Policy",
              "default-src 'self' 'unsafe-inline' 'unsafe-eval' data:; frame-ancestors 'self'");
          res.setHeader("X-Frame-Options", "SAMEORIGIN");
        }
        chain.doFilter(req, res);
      }
    };

    FilterRegistrationBean<OncePerRequestFilter> frb = new FilterRegistrationBean<>(filter);
    frb.addUrlPatterns("/h2-console/*");
    frb.setOrder(Ordered.HIGHEST_PRECEDENCE);
    return frb;
  }
}