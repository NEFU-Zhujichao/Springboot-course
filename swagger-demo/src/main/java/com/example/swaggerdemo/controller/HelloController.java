package com.example.swaggerdemo.controller;

import com.example.swaggerdemo.entity.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
//@Api：用在请求的类上，表示对类的说明
//    tags="说明该类的作用，可以在UI界面上看到的注解"
// 剩下还有一些注解，用的时候再百度即可，不难。
@Api(tags = "HelloController!")
@RestController
@RequestMapping("/api/")
public class HelloController {

    @GetMapping("/hello")
    public Map hello(){
        return Map.of("msg","HelloWorld");
    }

    @ApiOperation("获取用户参数")
    @PostMapping("/user")
    public Map user(@ApiParam("用户参数") @RequestBody User user){
        System.out.println(user.getUsername());
        return Map.of("user",user);
    }
}
