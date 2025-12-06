package com.example.space.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/h2-console/**",
                                "/graphiql/**",
                                "/graphiql"
                        ).permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "STAFF", "STUDENT")
                    .requestMatchers(HttpMethod.POST, "/api/**").hasAnyRole("ADMIN", "STAFF")
                    .requestMatchers(HttpMethod.PUT, "/api/**").hasAnyRole("ADMIN", "STAFF")
                    .requestMatchers(HttpMethod.DELETE, "/api/**").hasAnyRole("ADMIN", "STAFF")
                    .requestMatchers("/graphql").authenticated()
                    .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults());

        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
