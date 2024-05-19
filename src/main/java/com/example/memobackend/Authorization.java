package com.example.memobackend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
public class Authorization {
    @RequestMapping(value = "/user")
    public Principal user(Principal principal) throws JsonProcessingException, InvalidDefinitionException {
        return (new LoginWithGoogle()).user(principal);
    }

    @RequestMapping(value = "/user_info")
    public Map<String, Object> userInfo(Principal principal){
        return (new LoginWithGoogle()).userInfo(principal);
    }
}
