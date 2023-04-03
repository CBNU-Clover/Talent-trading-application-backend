package com.backend.backend.controller;

import com.backend.backend.domain.Member;
import com.backend.backend.dto.LoginDTO;
import com.backend.backend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/members/login")
    public void registerMember(@RequestBody LoginDTO loginDTO)
    {

        loginService.login(loginDTO);
    }

}
