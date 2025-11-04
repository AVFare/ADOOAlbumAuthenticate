package com.adoo.album.service.api;

import com.adoo.album.model.dto.LoginRequestDTO;
import com.adoo.album.model.dto.LoginResponseDTO;
import com.adoo.album.model.dto.RegisterRequestDTO;
import com.adoo.album.model.dto.RegisterResponseDTO;

public interface IAuthService {

    public RegisterResponseDTO register(RegisterRequestDTO request);
    public LoginResponseDTO login (LoginRequestDTO request);

}
