package com.tareqmy.microservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tareqmy at 6/5/22
 */
@RestController
@RequestMapping("/hello")
public class HelloResource {

    @GetMapping("/")
    public String getHello() {
        return "Hello World!";
    }
}
