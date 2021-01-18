package com.example.swaggerdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/")
public class HelloController {

    @GetMapping("/hello")
    public Map hello(){
        return Map.of("msg","HelloWorld");
    }
}
