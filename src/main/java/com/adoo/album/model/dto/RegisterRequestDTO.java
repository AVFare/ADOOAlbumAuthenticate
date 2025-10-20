package com.adoo.album.model.dto;

import com.adoo.album.model.entity.Role;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {
    private String username;
    private String password;
    private Role role;
}
