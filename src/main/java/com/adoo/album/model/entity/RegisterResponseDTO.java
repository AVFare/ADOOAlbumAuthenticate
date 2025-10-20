package com.adoo.album.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponseDTO {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("role")
    private Role role;
}

