package com.example.springboottask.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TimerService {
    // 分 秒 时 日 月 星期
    // ,->and /增幅 -区间 ?占位 *通配符
    @Scheduled(cron = "*/3 * * * * *")
    public void getSalary(){
        System.out.println("you have salary");
    }
}
