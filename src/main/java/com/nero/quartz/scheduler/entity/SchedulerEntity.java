package com.nero.quartz.scheduler.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.nero.quartz.scheduler.bo.BaseScheduleJobEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Nero
 */
@TableName("scheduler")
@Data
@EqualsAndHashCode(callSuper = true)
public class SchedulerEntity extends BaseScheduleJobEntity {

    private String remark;
}
