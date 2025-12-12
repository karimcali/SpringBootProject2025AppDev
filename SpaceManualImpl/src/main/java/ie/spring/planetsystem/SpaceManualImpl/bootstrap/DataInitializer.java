package ie.spring.planetsystem.SpaceManualImpl.bootstrap;

import ie.spring.planetsystem.SpaceManualImpl.dto.UserCreateDTO;
import ie.spring.planetsystem.SpaceManualImpl.service.MyUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final MyUserService myUserService;

    @Override
    public void run(String... args) {
        // Admin user
        createUserIfMissing("admin1", "admin123", "ADMIN");

        // Staff user
        createUserIfMissing("staff1", "staff123", "STAFF");

        // Student user
        createUserIfMissing("student1", "student123", "STUDENT");
    }

    private void createUserIfMissing(String username, String rawPassword, String role) {
        try {
            myUserService.getUserByUsername(username);
            log.info("User {} already exists, skipping", username);
        } catch (Exception e) {
            log.info("Creating user {} with role {}", username, role);
            UserCreateDTO dto = UserCreateDTO.builder()
                    .username(username)
                    .password(rawPassword)
                    .role(role)
                    .build();
            myUserService.createUser(dto);
        }
    }
}
