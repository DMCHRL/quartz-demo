package com.nero.quartz.scheduler.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nero.quartz.scheduler.entity.SchedulerEntity;
import com.nero.quartz.scheduler.mapper.SchedulerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author Nero
 * 任务表服务层
 */
@Service
public class SchedulerService extends ServiceImpl<SchedulerMapper, SchedulerEntity> {

    @Autowired
    private SchedulerManager schedulerManager;

    /**
     * 初始启动定时任务
     */
    @PostConstruct
    public void initSchedule(){
        List<SchedulerEntity> schedulerEntities = baseMapper.selectList(null);
        if( !CollectionUtils.isEmpty(schedulerEntities) ){
            schedulerEntities.forEach( schedulerEntity -> {
                schedulerManager.saveOrUpdate(schedulerEntity);
            });
        }
    }
}
