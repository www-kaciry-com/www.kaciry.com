package com.kaciry.controller;

import com.kaciry.entity.ResultBean;
import com.kaciry.entity.User;
import com.kaciry.service.Impl.UserServiceImpl;
import com.kaciry.utils.GetCookiesValueByKey;
import com.kaciry.utils.RSAUtils;
import com.kaciry.utils.TokenRS256;
import com.kaciry.utils.TokenUtils;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kaciry
 * @date 2019/10/25 17:16
 * @description 有关用户登陆注册的Controller
 */
@Controller
public class LoginController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * @param username 用户名
     * @return java.util.Map
     * @author kaciry
     * @description 生成公钥和私钥
     * @date 2019/11/7 12:48
     **/
    @PostMapping(value = "/rsaKey1")
    @ResponseBody
    public Map generateRSAKey(String username) {
        try {
            // 获取公钥和私钥
            HashMap<String, Object> keys = RSAUtils.getKeys();
            RSAPublicKey publicKey = (RSAPublicKey) keys.get("public");
            RSAPrivateKey privateKey = (RSAPrivateKey) keys.get("private");
            // 保存私钥到 redis，也可以保存到数据库
            redisTemplate.opsForValue().set(username, privateKey);
            // 将公钥传到前端
            Map<String, String> map = new HashMap<>();
            // 注意返回modulus和exponent以16为基数的BigInteger的字符串表示形式
            map.put("modulus", publicKey.getModulus().toString(16));
            map.put("exponent", publicKey.getPublicExponent().toString(16));
            return map;
        } catch (NoSuchAlgorithmException e) {
            System.out.println("fail2");
            return null;
        }
    }

    @PostMapping(value = "/rsaKey2")
    @ResponseBody
    public ResultBean checkRSAKey(String username, String password, boolean checkbox, HttpServletResponse response) throws Exception {
        Object object = redisTemplate.opsForValue().get(username);
        redisTemplate.delete(username);
        // 解密
        String decryptByPrivateKey = RSAUtils.decryptByPrivateKey(password, (RSAPrivateKey) object);

        User res = userServiceImpl.login(username, decryptByPrivateKey);
        if (res != null && res.getUsername().equals(username) && res.getUserPassword().equals(decryptByPrivateKey)) {
            //获取钥匙
            RSAKey key = TokenUtils.getKey();
            //设置token过期时间
            int exp = 1000 * 60 * 60;
            //生成Token
            String token = TokenRS256.TokenTest(username, key, exp);
            //保存Token到redis
            redisTemplate.opsForValue().set(username + "_key", key);
            //将个人不敏感信息和Token放入Cookie
            Cookie cookie1 = new Cookie("Token", token);
            Cookie cookie2 = new Cookie("head", res.getUserHeadIcon());
            Cookie cookie3 = new Cookie("nickname", res.getUserNickName());
            Cookie cookie4 = new Cookie("username", res.getUsername());
            Cookie cookie5 = new Cookie("level", res.getUserLevel());
            Cookie cookie6 = new Cookie("vip", res.getIsVip());
            if (checkbox) {
                //10天有效期
                cookie1.setMaxAge(10 * 24 * 3600);
                cookie2.setMaxAge(10 * 24 * 3600);
                cookie3.setMaxAge(10 * 24 * 3600);
                cookie4.setMaxAge(10 * 24 * 3600);
                cookie5.setMaxAge(10 * 24 * 3600);
                cookie6.setMaxAge(10 * 24 * 3600);
            }
            //添加cookie
            response.addCookie(cookie1);
            response.addCookie(cookie2);
            response.addCookie(cookie3);
            response.addCookie(cookie4);
            response.addCookie(cookie5);
            response.addCookie(cookie6);
            return new ResultBean<>("success");
        } else {
            return new ResultBean<>("用户名或密码错误！");
        }

    }



    /**
     * @param request  request请求
     * @param response response
     * @return java.lang.String
     * @author kaciry
     * @description 用户注销，清除Session
     * @date 2019/10/25 17:21
     **/
    @RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        //删除redis里的token
        String value = GetCookiesValueByKey.getValue(request, "username");
        redisTemplate.delete(value + "_key");
        //消除session
        HttpSession session = request.getSession();
        session.invalidate();
        //清除cookie，并且退出
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        return "redirect:/";

    }

    /**
     * @param session session
     * @param user    User实体
     * @return java.lang.String
     * @author kaciry
     * @description 用户注册
     * @date 2019/10/25 17:19
     **/
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String register(HttpSession session, @ModelAttribute(value = "user") User user, Map<String, String> map) {
        user.setUserCoins(0);
        user.setUserNickName(user.getUsername());
        user.setIsVip("未开通");
        user.setUserLevel("1");
        user.setUserHeadIcon("/static/img/null.jpeg");
        String result = userServiceImpl.register(user);
        if (result.equals("error")) {
            map.put("msgs", "账号已存在！");
            return "public/signup";
        } else {
            User resUser = userServiceImpl.login(user.getUsername(), user.getUserPassword());
            session.setAttribute("user", resUser);
            session.setAttribute("username", resUser.getUsername());
            return "redirect:/";
        }

    }
}
