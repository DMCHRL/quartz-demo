package com.nero.quartz.scheduler.bo;

import lombok.Data;

/**
 * @author Nero
 *  任务基础配置实体
 */
@Data
public class BaseScheduleJobEntity {
    public final static Integer ENABLE_STATUS = 1;

    private String id;
    private String beanName;
    private String cron;
    private Integer enable;
}
