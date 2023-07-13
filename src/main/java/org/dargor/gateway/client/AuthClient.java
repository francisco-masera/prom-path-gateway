package org.dargor.gateway.client;

import org.dargor.gateway.dto.LoginRequestDto;
import org.dargor.gateway.dto.SignUpRequestDto;
import org.dargor.gateway.dto.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "AuthClient",
        url = "${feign.auth-svc.host}:${feign.auth-svc.port}/${feign.auth-svc.id}/${feign.auth-svc.url}"
)
public interface AuthClient {

    @GetMapping("/${feign.auth-svc.sign-up-url}")
    UserResponseDto signUp(@RequestBody SignUpRequestDto signUpRequestDto);

    @PostMapping("/${feign.auth-svc.login-url}")
    UserResponseDto login(@RequestBody LoginRequestDto products);

}
