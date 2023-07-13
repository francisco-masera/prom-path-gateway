package org.dargor.gateway.service;

import org.dargor.gateway.dto.LoginRequestDto;
import org.dargor.gateway.dto.SignUpRequestDto;
import org.dargor.gateway.dto.UserResponseDto;

public interface AuthService {

    UserResponseDto signup(SignUpRequestDto request);

    UserResponseDto login(LoginRequestDto request);

}
