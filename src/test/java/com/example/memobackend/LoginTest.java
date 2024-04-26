package com.example.memobackend;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class UserInfoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OAuth2AuthenticationToken token;

    @Mock
    private Principal principal;

    @Mock
    private DefaultOAuth2User userInfo;

    @Test
    @WithMockUser
    public void apiUserInfoTest() throws Exception {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("sub", "test_id");
        attributes.put("given_name", "Test User");
        attributes.put("email", "test@example.com");

        DefaultOAuth2User userInfo = new DefaultOAuth2User(null, attributes, "sub");

        when(token.getPrincipal()).thenReturn(userInfo);

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(token);

        //先當作錯誤的登入方式會產生錯誤...?   * isForbidden=403
        mockMvc.perform(MockMvcRequestBuilders.get("/user_info")
                        .principal(principal))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

}