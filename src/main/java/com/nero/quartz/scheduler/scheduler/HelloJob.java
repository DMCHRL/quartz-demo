package com.nero.quartz.scheduler.scheduler;

import com.nero.quartz.scheduler.service.TestService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Nero
 * 任务执行bean
 */
@Service
public class HelloJob extends QuartzJobBean {

    @Autowired
    private TestService testService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        testService.testMethod();
        System.out.println("当前时间：  "+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
