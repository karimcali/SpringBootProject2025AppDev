package ie.spring.planetsystem.SpaceManualImpl.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyUser {
    
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;   // BCrypt hashed

    @Column(nullable = false)
    private String role;       // ADMIN, STAFF, STUDENT

    private boolean enabled = true;

    private boolean unlocked = true;
}
