package com.planets.api.config;

import com.planets.api.entity.Moon;
import com.planets.api.entity.Planet;
import com.planets.api.entity.User;
import com.planets.api.repository.MoonRepository;
import com.planets.api.repository.PlanetRepository;
import com.planets.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PlanetRepository planetRepository;
    private final MoonRepository moonRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        log.info("Loading sample data...");

        // Create Users
        User admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin123"))
                .role("ADMIN")
                .build();

        User staff = User.builder()
                .username("staff")
                .password(passwordEncoder.encode("staff123"))
                .role("STAFF")
                .build();

        User student = User.builder()
                .username("student")
                .password(passwordEncoder.encode("student123"))
                .role("STUDENT")
                .build();

        userRepository.save(admin);
        userRepository.save(staff);
        userRepository.save(student);
        log.info("Created {} users", 3);

        // Create Planets
        Planet earth = Planet.builder()
                .name("Earth")
                .type("Terrestrial")
                .radiusKm(6371.0)
                .massKg(5.972e24)
                .orbitalPeriodDays(365.25)
                .build();

        Planet mars = Planet.builder()
                .name("Mars")
                .type("Terrestrial")
                .radiusKm(3389.5)
                .massKg(6.39e23)
                .orbitalPeriodDays(687.0)
                .build();

        Planet jupiter = Planet.builder()
                .name("Jupiter")
                .type("Gas Giant")
                .radiusKm(69911.0)
                .massKg(1.898e27)
                .orbitalPeriodDays(4333.0)
                .build();

        Planet saturn = Planet.builder()
                .name("Saturn")
                .type("Gas Giant")
                .radiusKm(58232.0)
                .massKg(5.683e26)
                .orbitalPeriodDays(10759.0)
                .build();

        Planet neptune = Planet.builder()
                .name("Neptune")
                .type("Ice Giant")
                .radiusKm(24622.0)
                .massKg(1.024e26)
                .orbitalPeriodDays(60190.0)
                .build();

        planetRepository.save(earth);
        planetRepository.save(mars);
        planetRepository.save(jupiter);
        planetRepository.save(saturn);
        planetRepository.save(neptune);
        log.info("Created {} planets", 5);

        // Create Moons
        Moon luna = Moon.builder()
                .name("Luna (Moon)")
                .diameterKm(3474.8)
                .orbitalPeriodDays(27.3)
                .planet(earth)
                .build();

        Moon phobos = Moon.builder()
                .name("Phobos")
                .diameterKm(22.4)
                .orbitalPeriodDays(0.319)
                .planet(mars)
                .build();

        Moon deimos = Moon.builder()
                .name("Deimos")
                .diameterKm(12.4)
                .orbitalPeriodDays(1.263)
                .planet(mars)
                .build();

        Moon io = Moon.builder()
                .name("Io")
                .diameterKm(3643.2)
                .orbitalPeriodDays(1.769)
                .planet(jupiter)
                .build();

        Moon europa = Moon.builder()
                .name("Europa")
                .diameterKm(3121.6)
                .orbitalPeriodDays(3.551)
                .planet(jupiter)
                .build();

        Moon ganymede = Moon.builder()
                .name("Ganymede")
                .diameterKm(5268.2)
                .orbitalPeriodDays(7.155)
                .planet(jupiter)
                .build();

        Moon callisto = Moon.builder()
                .name("Callisto")
                .diameterKm(4820.6)
                .orbitalPeriodDays(16.689)
                .planet(jupiter)
                .build();

        Moon titan = Moon.builder()
                .name("Titan")
                .diameterKm(5149.5)
                .orbitalPeriodDays(15.945)
                .planet(saturn)
                .build();

        Moon triton = Moon.builder()
                .name("Triton")
                .diameterKm(2706.8)
                .orbitalPeriodDays(5.877)
                .planet(neptune)
                .build();

        moonRepository.save(luna);
        moonRepository.save(phobos);
        moonRepository.save(deimos);
        moonRepository.save(io);
        moonRepository.save(europa);
        moonRepository.save(ganymede);
        moonRepository.save(callisto);
        moonRepository.save(titan);
        moonRepository.save(triton);
        log.info("Created {} moons", 9);

        log.info("Sample data loaded successfully!");
        log.info("Test credentials:");
        log.info("  Admin    - username: admin,   password: admin123");
        log.info("  Staff    - username: staff,   password: staff123");
        log.info("  Student  - username: student, password: student123");
    }
}
