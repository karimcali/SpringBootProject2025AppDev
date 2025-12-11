package ie.spring.planetsystem.SpaceManualImpl.security;

import ie.spring.planetsystem.SpaceManualImpl.entities.MyUser;
import ie.spring.planetsystem.SpaceManualImpl.repositories.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final MyUserRepository myUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MyUser myUser = myUserRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found: " + username));

        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(myUser.getRole());

        return new User(
                myUser.getUsername(),
                myUser.getPassword(),
                myUser.isEnabled(),
                true,   // accountNonExpired
                true,   // credentialsNonExpired
                myUser.isUnlocked(),
                Collections.singletonList(authority)
        );
    }
}