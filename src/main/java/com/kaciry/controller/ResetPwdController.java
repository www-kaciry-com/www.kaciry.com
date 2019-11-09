package com.kaciry.controller;

import com.kaciry.service.Impl.CaptchaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author kaciry
 * @date 2019/10/25 17:22
 * @description 有关用户重置密码的Controller
 */
@Controller
public class ResetPwdController {

    @Autowired
    CaptchaServiceImpl verificationCodeService;

    /**
     * @param receiver 发送验证码的用户
     * @return java.lang.String
     * @author kaciry
     * @description 用户重置密码
     * @date 2019/10/25 17:25
     **/
    @PostMapping("/postResetPwd")
    @ResponseBody
    public String sendCaptcha(String receiver) {
        return verificationCodeService.sendCaptcha(receiver);
    }

}
