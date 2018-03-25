package com.neweagle.api.quart;

import com.neweagle.api.module.sys.timer.TestTimer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

/**
 * @author: tjp
 * @description:
 * @date:create in 17:40 2018/3/25
 * @version: 1.0.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class QuartTest {
    /**
     * 注入任务调度器
     */
    @Autowired
    private Scheduler scheduler;

    @Test
    public void addJob() throws Exception{
        //设置开始时间为1分钟后
        long startAtTime = System.currentTimeMillis() + 1000 * 60;
        //任务名称
        String name = UUID.randomUUID().toString();
        //任务所属分组
        String group = TestTimer.class.getName();
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(TestTimer.class).withIdentity(name, group).build();
        //创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group).startAt(new Date(startAtTime)).build();
        //将触发器与任务绑定到调度器内
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
