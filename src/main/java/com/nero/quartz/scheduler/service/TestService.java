package com.nero.quartz.scheduler.service;

import org.springframework.stereotype.Service;

/**
 * @author Nero
 */
@Service
public class TestService {

    public void testMethod(){
        System.out.println("测试注入Bean方法....");
    }
}
