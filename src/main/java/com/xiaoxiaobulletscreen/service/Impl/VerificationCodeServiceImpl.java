package com.xiaoxiaobulletscreen.service.Impl;

import com.xiaoxiaobulletscreen.Utils.Email;
import com.xiaoxiaobulletscreen.entity.VCode;
import com.xiaoxiaobulletscreen.service.VerificationCodeService;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {
    @Override
    public String sendVCode(String receiver) {
        //创建时间实体对象
        VCode vCode = new VCode();
        //创建邮件文字描述
        String wordDescribePrefix = "【筱筱弹幕网】尊敬的用户：您的校验码：";
        String wordDescribeSuffix = "  工作人员不会索取，请勿泄露！";
        //以时间戳获取验证码,6位
        String vCodeRes = vCode.getRes();
        try {
            //发送邮件给receiver对象
            Email.sendEmail(wordDescribePrefix + vCodeRes + wordDescribeSuffix, receiver);
            return vCodeRes;
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }

    }
}
