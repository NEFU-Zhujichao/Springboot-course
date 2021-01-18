package com.example.springsecurity;

import com.example.springsecurity.entity.User;
import com.example.springsecurity.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@SpringBootTest
@Slf4j
public class MyTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private DataSource dataSource;
    @Test
    public void test(){
        System.out.println(dataSource);
        System.out.println(userRepository.findUserById(1));
    }
    @Test
    public void test_addUser(){
        User user = new User();
        user.setName("chao");
        user.setPassword(encoder.encode("123456"));
        User user2 = new User();
        user2.setName("chao2");
        user2.setPassword(encoder.encode("123456"));
        userRepository.save(user);
        userRepository.save(user2);
    }
}
