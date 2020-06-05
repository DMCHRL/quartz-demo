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
 * 全局调度任务类
 * 任务放进内存中，未持久化到数据库
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
