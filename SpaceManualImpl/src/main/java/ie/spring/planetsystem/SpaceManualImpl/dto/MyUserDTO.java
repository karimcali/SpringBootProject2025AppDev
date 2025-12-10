package ie.spring.planetsystem.SpaceManualImpl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyUserDTO {

    private Long userId;
    private String username;
    private String role;
}

