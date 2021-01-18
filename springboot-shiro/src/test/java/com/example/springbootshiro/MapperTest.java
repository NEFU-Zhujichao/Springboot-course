package com.example.springbootshiro;

import com.example.springbootshiro.repository.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MapperTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void test_mapper(){
        System.out.println(userMapper.findUserByName("朱xx"));
    }
}
