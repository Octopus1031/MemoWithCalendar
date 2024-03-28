package com.example.memobackend;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringBootEx {
    public static void main(String[] args) {

    }
    @RequestMapping
    public String sayHello() {
        return "hi";
    }
}
