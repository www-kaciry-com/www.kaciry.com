package com.kaciry.service.Impl;

import com.kaciry.entity.ResultBean;
import com.kaciry.entity.UserChatDO;
import com.kaciry.dao.UserChatDao;
import com.kaciry.service.UserChatService;
import com.kaciry.utils.SensitiveWordFilter;
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
    public ResultBean saveUserChatMsg(UserChatDO userChatDO) {
        // 加载敏感词库
        SensitiveWordFilter filter = new SensitiveWordFilter();
        // 比对敏感词
        Set<String> set = filter.getSensitiveWord(userChatDO.getContent(), 1);
        //存在敏感词
        if (set.size() > 0) {
            ResultBean<UserChatDO> resultBean = new ResultBean<>();
            resultBean.setCode(502);
            resultBean.setMsg("【 请勿发送敏感词汇！】");
            resultBean.setData(userChatDO);
            return resultBean;
        } else {//不存在敏感词，保存并返回
            if (userChatDao.insertUserChatMsg(userChatDO)) {
                return new ResultBean<>(userChatDO);
            } else {
                return new ResultBean<>("服务器出错！");
            }
        }

    }

    @Override
    public List<UserChatDO> getPrivateMsg(String senderIdentityDocument, String receiverIdentityDocument) {
        List<UserChatDO> list1 = userChatDao.queryChatMsg(receiverIdentityDocument, senderIdentityDocument);
        List<UserChatDO> list2 = userChatDao.queryChatMsg(senderIdentityDocument, receiverIdentityDocument);
        list2.addAll(list1);
        //Java8 按照某个字段进行排序
        list2.sort(Comparator.comparingLong(UserChatDO::getUserChatIdentityDocument));
        // TODO: 2019/11/19 排序
//        Arrays.sort(new List[]{list2});
        return list2;
    }

    @Override
    public List<UserChatDO> getNewMsg(String senderIdentityDocument, String receiverIdentityDocument, int userChatIdentityDocument) {
        return userChatDao.getNewMsg(senderIdentityDocument, receiverIdentityDocument, userChatIdentityDocument);
    }
}
