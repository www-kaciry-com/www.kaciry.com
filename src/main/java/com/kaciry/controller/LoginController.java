package com.kaciry.controller;

import com.kaciry.entity.ResultBean;
import com.kaciry.entity.User;
import com.kaciry.service.Impl.UserServiceImpl;
import com.kaciry.utils.GetCookiesValueByKey;
import com.kaciry.utils.RSAUtils;
import com.kaciry.utils.TokenUtils;
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

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login() {
        return "signin";
    }

    @RequestMapping(value = {"/index"})
    public String index(HttpServletRequest request) {
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null && cookies.length > 0) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("Token")) {
//                    String username = TokenUtils.checkToken(cookie.getValue());
//
//                    User user = userServiceImpl.login(username);
//                    request.getSession().setAttribute("user", user);
//                    request.getSession().setAttribute("username", user.getUsername());
//                    break;
//                }
//            }
//        }
        return "index";
    }

    @RequestMapping(value = "/loginPage", method = RequestMethod.POST)
    public String login(HttpSession session, @ModelAttribute(value = "user") User user, Map<String, String> map) {
        User res = userServiceImpl.login(user.getUsername(), user.getUserPassword());
        if (res != null && res.getUsername().equals(user.getUsername()) && res.getUserPassword().equals(user.getUserPassword())) {
            session.setAttribute("user", res);
            session.setAttribute("username", res.getUsername());
            return "redirect:/";
        } else {
            map.put("msg", "用户名或密码错误！");
            return "signin";
        }
    }

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
    public ResultBean checkRSAKey(String username, String password, boolean checkbox, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Object object = redisTemplate.opsForValue().get(username);
        redisTemplate.delete(username);
        // 解密
        String decryptByPrivateKey = RSAUtils.decryptByPrivateKey(password,(RSAPrivateKey) object);

        User res = userServiceImpl.login(username, decryptByPrivateKey);
        if (res != null && res.getUsername().equals(username) && res.getUserPassword().equals(decryptByPrivateKey)) {
            request.getSession().setAttribute("user", res);
//            request.getSession().setAttribute("username", res.getUsername());
            //生成Token
            String token = TokenUtils.getToken(username);
            //保存Token到redis
            redisTemplate.opsForValue().set(username + "_token", token);
            //设置JSESSION和Token放入Cookie
            Cookie cookie1 = new Cookie("Token", token);
            Cookie cookie2 = new Cookie("head", res.getUserHeadIcon());
            Cookie cookie3 = new Cookie("nickname", res.getUserNickName());
            if (checkbox) {
                //10天有效期
                cookie1.setMaxAge(10 * 24 * 3600);
                cookie2.setMaxAge(10 * 24 * 3600);
                cookie3.setMaxAge(10 * 24 * 3600);
            }
            //添加cookie
            response.addCookie(cookie1);
            response.addCookie(cookie2);
            response.addCookie(cookie3);
            return new ResultBean<>("success");
        } else {
            return new ResultBean<>("用户名或密码错误！");
        }

    }

    @GetMapping(value = "/")
    public String loginIndex() {
        //登陆成功，跳转到主页
        return "redirect:/index";

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
        String value = GetCookiesValueByKey.getValue(request);
        redisTemplate.delete(TokenUtils.checkToken(value) + "_token");
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

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerGet() {
        return "signup";
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
            return "signup";
        } else {
            User resUser = userServiceImpl.login(user.getUsername(), user.getUserPassword());
            session.setAttribute("user", resUser);
            session.setAttribute("username", resUser.getUsername());
            return "redirect:/";
        }

    }
}
