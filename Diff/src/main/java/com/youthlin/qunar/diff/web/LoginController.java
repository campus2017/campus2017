package com.youthlin.qunar.diff.web;

import com.youthlin.qunar.diff.common.Constrant;
import com.youthlin.qunar.diff.model.User;
import com.youthlin.qunar.diff.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by youthlin.chen on 2016-11-17 017.
 * login
 */
@Controller
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    private static final String LOGIN = "login";
    private static final String LIST = "list";
    private static final String ERROR = "error";
    @Resource
    private UserService userService;

    @RequestMapping(value = {LOGIN}, method = {RequestMethod.GET})
    public String login(@RequestParam(value = "error", required = false, defaultValue = "") String error,
                        HttpSession session, Model model) {
        model.addAttribute(ERROR, error);
        return LOGIN;
    }

    @RequestMapping(value = {LOGIN + ".do"}, method = {RequestMethod.POST})
    public String login(@RequestParam(value = "username", defaultValue = "") String username,
                        @RequestParam(value = "password", defaultValue = "") String password,
                        HttpSession session, Model model) {
        if (!StringUtils.hasText(username) || !StringUtils.hasText(password)) {
            model.addAttribute(ERROR, "用户名和密码必填");
            return "redirect:" + LOGIN;
        }
        User user = userService.findByUsername(username);
        if (user == null) {
            model.addAttribute(ERROR, "用户不存在");
            return "redirect:" + LOGIN;
        }
        user = userService.login(username, password);
        if (user == null) {
            model.addAttribute(ERROR, "用户名或密码错误");
            return "redirect:" + LOGIN;
        }
        log.debug("登录成功{}", user);
        session.setAttribute(Constrant.CURRENT_USER, user);
        return "redirect:" + LIST;
    }

    @RequestMapping(value = {"logout"})
    public String logout(HttpSession session) {
        session.setAttribute(Constrant.CURRENT_USER, null);
        return "redirect:" + LOGIN;
    }
}
