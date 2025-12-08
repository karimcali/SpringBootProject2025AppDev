package com.planets.api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MoonCountDTO {

    private Long planetId;
    private String planetName;
    private Long moonCount;
}
