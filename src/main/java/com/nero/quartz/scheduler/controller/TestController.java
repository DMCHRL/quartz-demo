package com.nero.quartz.scheduler.controller;

import com.nero.quartz.scheduler.entity.SchedulerEntity;
import com.nero.quartz.scheduler.service.SchedulerManager;
import com.nero.quartz.scheduler.mapper.SchedulerMapper;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private SchedulerManager schedulerManager;

    @Autowired
    private SchedulerMapper schedulerMapper;

    @RequestMapping("/start")
    public String start(){
        SchedulerEntity schedulerEntity = schedulerMapper.selectOne(null);
        schedulerManager.saveOrUpdate(schedulerEntity);
        return "success";
    }

    @RequestMapping("/update")
    public String update(){
        SchedulerEntity schedulerEntity = schedulerMapper.selectOne(null);
        schedulerEntity.setCron("0/5 * * * * ?");
        schedulerManager.saveOrUpdate(schedulerEntity);
        return "success";
    }

    @RequestMapping("/pauseJob/{id}")
    public String pauseJob(@PathVariable String id)throws SchedulerException {
        schedulerManager.pauseJob(id);
        return "success";
    }

    @RequestMapping("/resumeJob/{id}")
    public String resumeJob(@PathVariable String id)throws SchedulerException {
        schedulerManager.resumeJob(id);
        return "success";
    }
}
