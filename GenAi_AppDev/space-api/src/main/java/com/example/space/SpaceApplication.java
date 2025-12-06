package com.example.space;

import com.example.space.domain.*;
import com.example.space.repository.AppUserRepository;
import com.example.space.repository.MoonRepository;
import com.example.space.repository.PlanetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;

@SpringBootApplication
public class SpaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpaceApplication.class, args);
    }

    @Bean
    CommandLineRunner dataLoader(
            PlanetRepository planetRepo,
            MoonRepository moonRepo,
            AppUserRepository userRepo,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            Planet earth = Planet.builder()
                    .name("Earth")
                    .type(PlanetType.TERRESTRIAL)
                    .radiusKm(new BigDecimal("6371"))
                    .massKg(new BigDecimal("5972000000000000000000000"))
                    .orbitalPeriodDays(new BigDecimal("365.25"))
                    .build();

            Planet mars = Planet.builder()
                    .name("Mars")
                    .type(PlanetType.TERRESTRIAL)
                    .radiusKm(new BigDecimal("3389.5"))
                    .massKg(new BigDecimal("641700000000000000000000"))
                    .orbitalPeriodDays(new BigDecimal("687"))
                    .build();

            Planet jupiter = Planet.builder()
                    .name("Jupiter")
                    .type(PlanetType.GAS_GIANT)
                    .radiusKm(new BigDecimal("69911"))
                    .massKg(new BigDecimal("1898000000000000000000000000"))
                    .orbitalPeriodDays(new BigDecimal("4332.59"))
                    .build();

            planetRepo.save(earth);
            planetRepo.save(mars);
            planetRepo.save(jupiter);

            moonRepo.save(Moon.builder()
                    .name("Moon")
                    .diameterKm(new BigDecimal("3474.8"))
                    .orbitalPeriodDays(new BigDecimal("27.32"))
                    .planet(earth)
                    .build());

            moonRepo.save(Moon.builder()
                    .name("Phobos")
                    .diameterKm(new BigDecimal("22.2"))
                    .orbitalPeriodDays(new BigDecimal("0.32"))
                    .planet(mars)
                    .build());

            moonRepo.save(Moon.builder()
                    .name("Deimos")
                    .diameterKm(new BigDecimal("12.6"))
                    .orbitalPeriodDays(new BigDecimal("1.26"))
                    .planet(mars)
                    .build());

            moonRepo.save(Moon.builder()
                    .name("Europa")
                    .diameterKm(new BigDecimal("3121.6"))
                    .orbitalPeriodDays(new BigDecimal("3.55"))
                    .planet(jupiter)
                    .build());

            userRepo.save(AppUser.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .role(Role.ADMIN)
                    .build());

            userRepo.save(AppUser.builder()
                    .username("staff")
                    .password(passwordEncoder.encode("staff123"))
                    .role(Role.STAFF)
                    .build());

            userRepo.save(AppUser.builder()
                    .username("student")
                    .password(passwordEncoder.encode("student123"))
                    .role(Role.STUDENT)
                    .build());
        };
    }
}
