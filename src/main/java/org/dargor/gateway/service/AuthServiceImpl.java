package org.dargor.gateway.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dargor.gateway.client.AuthClient;
import org.dargor.gateway.dto.LoginRequestDto;
import org.dargor.gateway.dto.SignUpRequestDto;
import org.dargor.gateway.dto.UserResponseDto;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthClient authClient;

    @Override
    public UserResponseDto signup(SignUpRequestDto request) {
        log.info(String.format("SignUp request: %s", request));
        var response = authClient.signUp(request);
        log.info(String.format("SignUp response: %s", response));
        return response;
    }

    @Override
    public UserResponseDto login(LoginRequestDto request) {
        log.info(String.format("Login request: %s", request));
        var response = authClient.login(request);
        log.info(String.format("Login response: %s", response));
        return response;
    }

}
