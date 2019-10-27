package com.xiaoxiaobulletscreen.service.Impl;

import com.xiaoxiaobulletscreen.Utils.SensitiveWordFilter;
import com.xiaoxiaobulletscreen.dao.UserChatDao;
import com.xiaoxiaobulletscreen.entity.ResultBean;
import com.xiaoxiaobulletscreen.entity.UserChatBean;
import com.xiaoxiaobulletscreen.service.UserChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * @author kaciry
 * @date 2019/10/26 13:05
 * @description 用户聊天Service实现类
 */
@Service
public class UserChatServiceImpl implements UserChatService {

    @Autowired
    private UserChatDao userChatDao;

    @Override
    public ResultBean saveUserChatMsg(UserChatBean userChatBean) {
        // 加载敏感词库
        SensitiveWordFilter filter = new SensitiveWordFilter();
        // 比对敏感词
        Set<String> set = filter.getSensitiveWord(userChatBean.getContent(), 1);
        //存在敏感词
        if (set.size() > 0) {
            ResultBean<UserChatBean> resultBean = new ResultBean<>();
            resultBean.setCode(502);
            resultBean.setMsg("【 请勿发送敏感词汇！】");
            resultBean.setData(userChatBean);
            return resultBean;
        } else {//不存在敏感词，保存并返回
            userChatDao.addUserChatMsg(userChatBean);
            return new ResultBean<>(userChatBean);
        }

    }

    @Override
    public List<UserChatBean> getPrivateMsg(String senderIdentityDocument, String receiverIdentityDocument) {
        List<UserChatBean> list1 = userChatDao.queryChatMsg(receiverIdentityDocument, senderIdentityDocument);
        List<UserChatBean> list2 = userChatDao.queryChatMsg(senderIdentityDocument, receiverIdentityDocument);
        list2.addAll(list1);
        //Java8 按照某个字段进行排序
        list2.sort(Comparator.comparingInt(UserChatBean::getUserChatIdentityDocument));
        return list2;
    }

    @Override
    public List<UserChatBean> getNewMsg(String senderIdentityDocument, int userChatIdentityDocument) {
        return userChatDao.getNewMsg(senderIdentityDocument, userChatIdentityDocument);
    }
}
