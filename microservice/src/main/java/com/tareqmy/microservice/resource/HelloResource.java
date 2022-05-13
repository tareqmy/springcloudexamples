package com.tareqmy.microservice.resource;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * Created by tareqmy at 6/5/22
 */
@RestController
public class HelloResource {

    private final Random random = new Random();

    @HystrixCommand(defaultFallback = "getHelloDefault")
    @GetMapping("/hello")
    public String getHelloWorld() {
        return getHelloSlow();
    }

    public String getHelloSlow() {
        int i = random.ints(500, 1100)
            .findFirst()
            .getAsInt();
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {

        }
        return "Hello World! >>> Fast";
    }

    public String getHelloDefault() {
        return "Hello World! >>> Default";
    }
}
