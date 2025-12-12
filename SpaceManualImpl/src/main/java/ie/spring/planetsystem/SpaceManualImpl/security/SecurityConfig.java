package ie.spring.planetsystem.SpaceManualImpl.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableMethodSecurity // enables @PreAuthorize
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // for Postman / Swagger (From Security lecture notes to disable, would not be done normally )
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth
                // We used Swagger UI and the H2 console in the labs,
                // so we keep these endpoints open here to make local testing easier.
                .requestMatchers(
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/h2-console/**"
                ).permitAll()

                // STUDENT, STAFF, ADMIN can read planets & moons
                .requestMatchers(HttpMethod.GET, "/api/planets/**", "/api/moons/**")
                    .hasAnyAuthority("STUDENT", "STAFF", "ADMIN")

                // only STAFF + ADMIN can modify planets & moons
                .requestMatchers("/api/planets/**", "/api/moons/**")
                    .hasAnyAuthority("STAFF", "ADMIN")

                // GraphQL user management – ADMIN only
                .requestMatchers("/graphql").hasAuthority("ADMIN")

                // anything else: authenticated
                .anyRequest().authenticated()
            )

            // HTTP Basic (Using HTTP Basic auth like in the security lecture examples.)
            .httpBasic(httpBasic -> {});

        // If you use H2 console:(Allow frames so the H2 database console can be displayed in the browser.)
        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
}
