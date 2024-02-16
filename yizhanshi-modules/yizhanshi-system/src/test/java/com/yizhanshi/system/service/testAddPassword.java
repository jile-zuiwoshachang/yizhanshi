package com.yizhanshi.system.service;

import com.yizhanshi.common.security.utils.SecurityUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testAddPassword {
    @Test
    public void testGetPassword(){
        String pass= SecurityUtils.encryptPassword("123456");
        System.out.println(pass);
    }
}
