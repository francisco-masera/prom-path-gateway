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
        url = "${routing.auth-svc.host}:${routing.auth-svc.port}/${routing.auth-svc.id}/${routing.auth-svc.url}"
)
public interface AuthClient {

    @GetMapping(value = "/${routing.auth-svc.sign-up-url}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    UserResponseDto signUp(@RequestBody SignUpRequestDto signUpRequestDto);

    @PostMapping(value = "/${routing.auth-svc.login-url}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    UserResponseDto login(@RequestBody LoginRequestDto products);

}
