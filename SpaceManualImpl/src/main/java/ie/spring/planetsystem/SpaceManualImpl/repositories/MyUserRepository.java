package ie.spring.planetsystem.SpaceManualImpl.repositories;

import ie.spring.planetsystem.SpaceManualImpl.entities.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {

    Optional<MyUser> findByUsername(String username);

}

