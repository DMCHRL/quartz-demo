package com.nero.quartz;

import com.nero.quartz.scheduler.service.SchedulerManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QuartzDemoApplicationTests {

    @Autowired
    private SchedulerManager schedulerService;

    @Test
    void contextLoads() {
    }

}
