package com.example.memobackend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Login {
    @GetMapping("/loginTest")
    public String login() {
        return "login";
    }

    @RequestMapping("/login_success")
    public String loginSuccess() {
        return "login success";
    }

    @RequestMapping("/login_failure")
    public String loginFailure() {
        return "login failure";
    }
}