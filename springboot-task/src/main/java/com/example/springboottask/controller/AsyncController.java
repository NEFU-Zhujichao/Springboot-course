package com.example.springboottask.controller;

import com.example.springboottask.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/")
public class AsyncController {
    @Autowired
    private AsyncService asyncService;

    @GetMapping("hello")
    public Map hello() {
        asyncService.hello();
        return Map.of("msg", "hello");
    }
}
