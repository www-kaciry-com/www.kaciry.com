package com.kaciry.service.Impl;

import com.kaciry.utils.Email;
import com.kaciry.entity.CaptchaDO;
import com.kaciry.service.CaptchaService;
import org.springframework.stereotype.Service;
/**
 * @author kaciry
 * @date 2019/10/26 13:05
 * @description 发送验证码Service实现类
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {
    @Override
    public String sendCaptchaToUser(String receiver) {
        //创建时间实体对象
        CaptchaDO captchaDO = new CaptchaDO();
        //创建邮件文字描述
        String wordDescribePrefix = "【筱筱弹幕网】尊敬的用户：您的校验码：";
        String wordDescribeSuffix = "  工作人员不会索取，请勿泄露！";
        //以时间戳获取验证码,6位
        String vCodeRes = captchaDO.getRes();
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
