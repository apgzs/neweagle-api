package com.neweagle.api.sign;

import com.neweagle.api.comm.utils.DateHelper;
import com.neweagle.api.comm.utils.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: tjp
 * @description:
 * @date:create in 21:14 2018/3/25
 * @version: 1.0.0
 **/
@Slf4j
public class SignTest {
    @Test
    public void createSign() throws Exception{
        long nowTime =  DateHelper.getCurrentTimeMillis();
        log.info("当前时间戳："+nowTime);
        Map<String,Object> map = new HashMap();
        map.put("mobile","13215096396");
        map.put("verifyCode","2131");
        map.put("password","123123");
        map.put("timestamp",nowTime);
        log.info(SignUtil.createSign(map,true));
    }
}
