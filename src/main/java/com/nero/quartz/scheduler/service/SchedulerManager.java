package com.nero.quartz.scheduler.service;

import com.nero.quartz.scheduler.bo.BaseScheduleJobEntity;
import com.nero.quartz.scheduler.entity.SchedulerEntity;
import org.quartz.*;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @author Nero
 * 全局调度任务类
 * 任务放进内存中，未持久化到数据库
 */
@Service
public class SchedulerManager implements ApplicationContextAware {

    /**
     * 任务前缀
     */
    private final static String TASK_PREFIX = "TASK_";
    /**
     * trigger前缀
     */
    private final static String TRIGGER_PREFIX = "TRIGGER_";
    private static ApplicationContext applicationContext;
    @Autowired
    private Scheduler scheduler;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext1) throws BeansException {
        applicationContext = applicationContext1;
    }

    /**
     * 创建或更新任务任务
     * @param task
     */
    public <T extends BaseScheduleJobEntity> void saveOrUpdate(T task) {
        // 构造cron表达式
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(task.getCron());
        // 构造触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(getTriggerName(task.getId()))
                .withSchedule(cronScheduleBuilder)
                .build();

        TriggerKey oldTriggerKey = TriggerKey.triggerKey(getTriggerName(task.getId()));
        Trigger oldTrigger = null;
        try{
            oldTrigger = scheduler.getTrigger(oldTriggerKey);
            if(oldTrigger != null){
                //更新任务
                scheduler.rescheduleJob(oldTriggerKey, trigger);
            }else{
                //新增任务
                Object object = applicationContext.getBean(task.getBeanName());
                Class clz = object.getClass();
                JobDetail jobDetail = JobBuilder.newJob(clz)
                        .withIdentity(getJobName(task.getId()))
                        .build();
                scheduler.scheduleJob(jobDetail,trigger);
            }
            //开启任务
            if (task.getEnable().equals(BaseScheduleJobEntity.ENABLE_STATUS) ){
                scheduler.start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 暂定任务
     * @param id
     * @throws SchedulerException
     */
    public void pauseJob( String id) throws SchedulerException {
        scheduler.pauseJob(JobKey.jobKey(getJobName(id)));
    }

    /**
     * 重启任务
     * @param id
     * @throws SchedulerException
     */
    public void resumeJob(String id) throws SchedulerException {
        scheduler.resumeJob(JobKey.jobKey(getJobName(id)));
    }

    /**
     * 删除任务
     * @param id
     * @throws SchedulerException
     */
    public void removeJob(String id) throws SchedulerException {
        scheduler.deleteJob(JobKey.jobKey(getJobName(id)));
    }

    private static String getJobName(String id){
        return TASK_PREFIX + id;
    }

    private static String getTriggerName(String id){
        return TRIGGER_PREFIX + id;
    }
}
