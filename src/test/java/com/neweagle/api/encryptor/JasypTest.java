package com.neweagle.api.encryptor;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: tjp
 * @description:
 * @date:create in 15:10 2018/3/23
 * @version: 1.0.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class JasypTest {


    @Autowired
    StringEncryptor stringEncryptor;

    @Test
    public void encryptPwd() {
        String result = stringEncryptor.encrypt("123123");
        System.out.println(result);
    }
}