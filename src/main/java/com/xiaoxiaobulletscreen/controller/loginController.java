package com.xiaoxiaobulletscreen.controller;

import com.xiaoxiaobulletscreen.entity.User;
import com.xiaoxiaobulletscreen.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class loginController {
    @Autowired
    private UserServiceImpl UserServiceImpl;

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
        User resUser = UserServiceImpl.login(user.getUsername(), user.getUserPassword());
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

    //进入注册页面，使用Get请求，REST风格的URL能更有雅的处理问题
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerGet() {
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String register(HttpSession session,
                           //这里和模板中的th:object="${user}"对应起来
                           @ModelAttribute(value = "user") User user) {
        user.setUserCoins(0);
        user.setUserNickName(user.getUsername());
        user.setIsVip("未开通");
        user.setUserLevel("1");
        user.setUserHeadIcon("/static/img/null.jpeg");
//        System.out.println(user);
        //使用userService处理业务
        String result = UserServiceImpl.register(user);
        //将结果放入model中，在模板中可以取到model中的值
//        model.addAttribute("result", result);
        User resUser = UserServiceImpl.login(user.getUsername(), user.getUserPassword());
        session.setAttribute("user", resUser);
        session.setAttribute("username", resUser.getUsername());
//        return response.encodeRedirectURL("/index");
        return "redirect:/";
    }
}
