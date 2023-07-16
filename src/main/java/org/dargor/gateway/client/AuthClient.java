package org.dargor.gateway.client;

import org.dargor.gateway.dto.LoginRequestDto;
import org.dargor.gateway.dto.SignUpRequestDto;
import org.dargor.gateway.dto.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "AuthClient",
        url = "${routing.auth-svc.host}:${routing.auth-svc.port}/${routing.auth-svc.id}/${routing.auth-svc.url}"
)
public interface AuthClient {

    @GetMapping("/${routing.auth-svc.sign-up-url}")
    UserResponseDto signUp(@RequestBody SignUpRequestDto signUpRequestDto);

    @PostMapping("/${routing.auth-svc.login-url}")
    UserResponseDto login(@RequestBody LoginRequestDto products);

}
