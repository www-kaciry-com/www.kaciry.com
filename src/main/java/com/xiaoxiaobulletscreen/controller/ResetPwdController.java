package com.xiaoxiaobulletscreen.controller;

import com.xiaoxiaobulletscreen.service.Impl.VerificationCodeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ResetPwdController {

    @Autowired
    VerificationCodeServiceImpl verificationCodeService;

    @GetMapping("/getResetPwd")
    public String resetPwd() {
        return "resetPwd";
    }

    @PostMapping("/postResetPwd")
    @ResponseBody
    public String sendVCode(String receiver) {
        return verificationCodeService.sendVCode(receiver);
    }

}
