package com.xiaoxiaobulletscreen.service;
/**
 * @author kaciry
 * @date 2019/10/26 13:05
 * @description 验证码Service接口
 */
public interface CaptchaService {
     /**
      * @author kaciry
      * @description  发送验证码
      * @date  2019/10/26 13:54
      * @param receiver 接受者的Email
      * @return java.lang.String
     **/
     String sendCaptcha(String receiver);
}
