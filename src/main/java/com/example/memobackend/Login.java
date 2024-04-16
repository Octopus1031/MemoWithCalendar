package com.example.memobackend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

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

//    要取得"想像的"使用者名稱要使用OAuth2AuthenticationToken
//    也就是下面那個function
//    @GetMapping("/username")
//    public String getUsername(Principal principal) {
//        return principal.getName();
//    }

    // 取得登入的使用者Google資訊
    @RequestMapping(value = "/user_info")
    public Map<String, Object> userInfo(Principal principal) {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) principal;
        OAuth2User userInfo = token.getPrincipal();

        // set user info
        User user = new User(userInfo.getAttribute("sub"), userInfo.getAttribute("given_name"), userInfo.getAttribute("email"));
        System.out.print("id: ");
        System.out.println(user.getId());
        System.out.print("name: ");
        System.out.println(user.getName());
        System.out.print("email: ");
        System.out.println(user.getEmail());

        return userInfo.getAttributes();
    }
}