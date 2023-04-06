package com.backend.backend.controller;

import com.backend.backend.dto.LoginDTO;
import com.backend.backend.jwt.JwtToken;
import com.backend.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService service;

    public UserController(UserService service)
    {
        this.service=service;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtToken>loginSuccess(@RequestBody Map<String,String>loginForm)
    {
        JwtToken token=service.login(loginForm.get("username"),loginForm.get("password"));
        // 로그인이 성공하면 토큰을 리턴한다.
        return ResponseEntity.ok(token);
    }

}
