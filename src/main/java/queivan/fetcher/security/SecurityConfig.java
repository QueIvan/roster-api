package queivan.fetcher.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.web.SecurityFilterChain;
import queivan.fetcher.security.AudienceValidator;

@EnableWebSecurity
public class SecurityConfig {
    @Value("${auth0.audience}")
    private String audience;
    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuer;

    @Bean
    JwtDecoder jwtDecoder() {
        NimbusJwtDecoder jwtDecoder = JwtDecoders.fromOidcIssuerLocation(issuer);

        OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(audience);
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);

        jwtDecoder.setJwtValidator(withAudience);

        return jwtDecoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/logs").hasAuthority("SCOPE_fetcher:logs_fetch")
                .mvcMatchers(HttpMethod.GET, "/paths").hasAuthority("SCOPE_fetcher:path_fetch_admin")
                .mvcMatchers(HttpMethod.GET, "/paths/*").permitAll()
                .mvcMatchers(HttpMethod.GET, "/configs").hasAuthority("SCOPE_fetcher:config_fetch_admin")
                .mvcMatchers(HttpMethod.GET, "/configs/fetch").permitAll()
                .mvcMatchers(HttpMethod.GET, "/configs/fetch/*").permitAll()
                .mvcMatchers(HttpMethod.GET, "/configs/*").permitAll()
                .mvcMatchers(HttpMethod.GET, "/rosters/*").permitAll()
                .mvcMatchers(HttpMethod.GET, "/replacements/*").hasAuthority("SCOPE_fetcher:config_fetch_admin")
                .mvcMatchers(HttpMethod.POST, "/rosters").hasAuthority("SCOPE_fetcher:config_fetch_admin")
                .mvcMatchers(HttpMethod.POST, "/paths").hasAuthority("SCOPE_fetcher:path_create")
                .mvcMatchers(HttpMethod.POST, "/configs").hasAuthority("SCOPE_fetcher:config_create")
                .mvcMatchers(HttpMethod.PUT, "/paths").hasAuthority("SCOPE_fetcher:path_update")
                .mvcMatchers(HttpMethod.PUT, "/configs").hasAuthority("SCOPE_fetcher:config_update")
                .mvcMatchers(HttpMethod.PUT, "/authorize/*").hasAuthority("SCOPE_fetcher:authorize")
                .mvcMatchers(HttpMethod.DELETE, "/paths/*/delete").hasAuthority("SCOPE_fetcher:path_delete")
                .mvcMatchers(HttpMethod.DELETE, "/configs/*/delete").hasAuthority("SCOPE_fetcher:config_delete")
                .and().cors()
                .and().oauth2ResourceServer().jwt();
        return http.build();
    }

}
