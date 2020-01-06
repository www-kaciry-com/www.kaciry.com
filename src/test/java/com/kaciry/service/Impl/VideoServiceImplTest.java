package com.kaciry.service.Impl;

import com.kaciry.entity.OperationsDO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional //数据库事务回滚
public class VideoServiceImplTest {

    @Autowired
    private VideoServiceImpl videoService;

    @Test
    public void operationOfStar() {
        OperationsDO operationsDO = new OperationsDO();
        operationsDO.setUsername("kaciry");
        operationsDO.setVideoFilename("av20200104191411187.mp4");
        boolean b = videoService.operationOfStar(operationsDO);
        //断言为true
        Assert.assertSame(true, b);
    }
}