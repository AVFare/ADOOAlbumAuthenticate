package com.adoo.album.service;

import com.adoo.album.model.entity.LoginRequestDTO;
import com.adoo.album.model.entity.LoginResponseDTO;
import com.adoo.album.model.entity.RegisterRequestDTO;
import com.adoo.album.model.entity.RegisterResponseDTO;

public interface IAuthService {

    public RegisterResponseDTO register(RegisterRequestDTO request);
    public LoginResponseDTO login (LoginRequestDTO request);

}
