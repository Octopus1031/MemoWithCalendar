package com.example.memobackend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class Login {

    // 取得登入的使用者資訊
    // 進入http://localhost:8080/user可以看到回傳的使用者資訊
    @RequestMapping(value = "/user")
    public Principal user(Principal principal) throws JsonProcessingException, InvalidDefinitionException {
        System.out.println("principal: ");
        System.out.println(principal.toString());
        return principal;
    }

    @GetMapping("/username")
    public String getUsername(Principal principal) {
        return principal.getName();
    }

}