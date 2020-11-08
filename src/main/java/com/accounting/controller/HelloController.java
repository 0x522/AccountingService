package com.accounting.controller;

import com.accounting.model.service.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;


@RestController
@RequestMapping("v1.0/hello")
public class HelloController {
    AtomicLong atomicLong = new AtomicLong();

    @GetMapping("v1.0/greeting")
    public Greeting sayHello(@RequestParam("name") String name) {
        return new Greeting(atomicLong.incrementAndGet(), String.format("Hello,%s", name));
    }
}
