package org.dargor.gateway.client;

import org.dargor.gateway.dto.LoginRequestDto;
import org.dargor.gateway.dto.SignUpRequestDto;
import org.dargor.gateway.dto.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "AuthClient",
        url = "${feign.auth-svc.host}:${feign.auth-svc.port}/${feign.auth-svc.url}"
)
public interface AuthClient {

    @GetMapping(value = "/${feign.auth-svc.sign-up-url}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    UserResponseDto signUp(@RequestBody SignUpRequestDto signUpRequestDto);

    @PostMapping(value = "/${feign.auth-svc.login-url}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    UserResponseDto login(@RequestBody LoginRequestDto loginRequestDto);

}
