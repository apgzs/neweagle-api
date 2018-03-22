package com.neweagle.api.comm.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * ${DESCRIPTION}
 *
 * @author 啊平
 * @create 2017-03-30 17:26
 **/
@Component
public class AppListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppListener.class);

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        LOGGER.info("系统启动成功！！");
    }

}
