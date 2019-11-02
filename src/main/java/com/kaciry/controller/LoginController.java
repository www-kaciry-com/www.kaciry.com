package com.kaciry.controller;

import com.kaciry.entity.User;
import com.kaciry.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login() {
        return "signin";
    }

    @RequestMapping(value = {"index"})
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/loginPage", method = RequestMethod.POST)
    public String login(HttpSession session, @ModelAttribute(value = "user") User user, Map<String, String> map) {
        User resUser = userServiceImpl.login(user.getUsername(), user.getUserPassword());
        // TODO: 2019/10/31 Bug在此
        if (resUser.getUsername().equals(user.getUsername()) && resUser.getUserPassword().equals(user.getUserPassword())) {
            session.setAttribute("user", resUser);
            session.setAttribute("username", resUser.getUsername());
            return "redirect:/";
        } else {
            map.put("msg", "用户名或密码错误！");
            return "signin";
        }

    }

    @GetMapping(value = "/")
    public String loginIndex() {
        //登陆成功，跳转到主页
        return "index";

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
    public String register(HttpSession session, @ModelAttribute(value = "user") User user) {
        user.setUserCoins(0);
        user.setUserNickName(user.getUsername());
        user.setIsVip("未开通");
        user.setUserLevel("1");
        user.setUserHeadIcon("/static/img/null.jpeg");
        String result = userServiceImpl.register(user);
        User resUser = userServiceImpl.login(user.getUsername(), user.getUserPassword());
        session.setAttribute("user", resUser);
        session.setAttribute("username", resUser.getUsername());
        return "redirect:/";
    }
}
