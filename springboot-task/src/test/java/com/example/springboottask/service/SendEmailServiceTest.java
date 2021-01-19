package com.example.springboottask.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;
import java.io.File;


@SpringBootTest
public class SendEmailServiceTest {

    @Autowired
    private SendEmailService service;
    @Test
    public void test_sendSimpleEmail(){
        service.sendSimpleEmail("HelloChao","邮件任务已完成","1394949540@qq.com","1394949540@qq.com");
    }

    @Test
    public void test_sendMultipartEmail(){
        File file = new File("C:\\Users\\13949\\Desktop\\1.png");
        try {
            service.sendMultipartEmail("HelloChao","<h1 style='color: red;border: 1px solid green;text-align: center;'>邮件任务已完成</h1>"
                    ,"1394949540@qq.com","1394949540@qq.com",true,"QQ皮肤.png",file);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
