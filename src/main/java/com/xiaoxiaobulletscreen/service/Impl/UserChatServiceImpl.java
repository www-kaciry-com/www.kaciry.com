package com.xiaoxiaobulletscreen.service.Impl;

import com.xiaoxiaobulletscreen.Utils.SensitivewordFilter;
import com.xiaoxiaobulletscreen.dao.UserChatDao;
import com.xiaoxiaobulletscreen.entity.ResultBean;
import com.xiaoxiaobulletscreen.entity.UserChatBean;
import com.xiaoxiaobulletscreen.service.UserChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserChatServiceImpl implements UserChatService {

    @Autowired
    private UserChatDao userChatDao;

    //保存私信
    @Override
    public ResultBean saveUserChatMsg(UserChatBean userChatBean) {

        SensitivewordFilter filter = new SensitivewordFilter();// 加载敏感词库
        Set<String> set = filter.getSensitiveWord(userChatBean.getContent(),1);// 比对敏感词
        //存在敏感词
        if (set.size()>0){
            ResultBean<UserChatBean> resultBean = new ResultBean<>();
            resultBean.setCode(502);
            resultBean.setMsg("请勿发送敏感词汇！");
            resultBean.setData(userChatBean);
            return resultBean;
        }else {//不存在敏感词，保存并返回
            userChatDao.addUserChatMsg(userChatBean);
            return new ResultBean<>(userChatBean);
        }

    }
}
