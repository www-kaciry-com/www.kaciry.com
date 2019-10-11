package com.xiaoxiaobulletscreen.service.Impl;

import com.xiaoxiaobulletscreen.Utils.SensitivewordFilter;
import com.xiaoxiaobulletscreen.dao.UserChatDao;
import com.xiaoxiaobulletscreen.entity.ResultBean;
import com.xiaoxiaobulletscreen.entity.UserChatBean;
import com.xiaoxiaobulletscreen.service.UserChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
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
            resultBean.setMsg("【 请勿发送敏感词汇！】");
            resultBean.setData(userChatBean);
            return resultBean;
        }else {//不存在敏感词，保存并返回
            userChatDao.addUserChatMsg(userChatBean);
            return new ResultBean<>(userChatBean);
        }

    }

    //获取私信
    @Override
    public List<UserChatBean> getPrivateMsg(String senderID, String receiverID) {
        List<UserChatBean> list1 = userChatDao.queryChatMsg(receiverID,senderID);
        List<UserChatBean> list2 = userChatDao.queryChatMsg(senderID,receiverID);
        list2.addAll(list1);
        //Java8 按照某个字段进行排序
        list2.sort(Comparator.comparingInt(UserChatBean::getUserChatID));
        return list2;
    }

    //获取新私信
    @Override
    public List<UserChatBean> getNewMsg(String senderID,int userChatID) {
        return userChatDao.getNewMsg(senderID,userChatID);
    }
}
