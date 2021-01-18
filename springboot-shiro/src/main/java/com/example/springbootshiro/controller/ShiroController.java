package com.example.springbootshiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ShiroController {
    @GetMapping({"/","/index"})
    public String index(Model model){
        model.addAttribute("msg","Hello Shiro");
        return "index";
    }

    @GetMapping("/user/add")
    public String add(){
        return "user/add";
    }

    @GetMapping("/user/update")
    public String update(){
        return "user/update";
    }
    @GetMapping("/toLogin")
    public String toLogin(){
        return "login";
    }
    @PostMapping("/login")
    public String login(String username,String password,Model model){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            return "index";
        }catch (UnknownAccountException e){
            model.addAttribute("msg","用户不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码不正确");
            return "login";
        }

    }

    @GetMapping("/auth")
    @ResponseBody
    public String authorization(){
        return "未授权";
    }
    @GetMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "index";
    }
}
