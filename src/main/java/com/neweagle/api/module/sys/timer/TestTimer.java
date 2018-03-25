package com.neweagle.api.module.sys.timer;

import com.neweagle.api.comm.utils.DateHelper;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;


/**
 * @author: tjp
 * @description: 测试quart
 * @date:create in 17:01 2018/3/25
 * @version: 1.0.0
 **/
@Slf4j
public class TestTimer extends QuartzJobBean {

    /**
     * 定时任务逻辑实现方法
     * 每当触发器触发时会执行该方法逻辑
     *
     * @param jobExecutionContext 任务执行上下文
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("任务时间：{}",DateHelper.getDateTime());
    }
}
