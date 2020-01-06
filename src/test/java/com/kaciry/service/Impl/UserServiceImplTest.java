package com.kaciry.service.Impl;

import com.kaciry.entity.ResultBean;
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
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void updateUserPassword() {
        ResultBean resultBean = userService.updateUserPassword("kaciry123", "1", "updateUserPassword");
        Assert.assertSame(new ResultBean<>("修改成功！").getData(), resultBean.getData());
    }
}