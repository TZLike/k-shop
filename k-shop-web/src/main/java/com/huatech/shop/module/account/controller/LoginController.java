package com.huatech.shop.module.account.controller;

import com.huatech.shop.base.controller.BaseController;
import com.huatech.shop.module.account.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author leek
 * @Date 2019-07-16 下午5:28
 * @Version 1.0
 * @Description
 */
@Controller
@Slf4j
public class LoginController extends BaseController {
    @RequestMapping(value = {"/admin/login"}, method = RequestMethod.GET)
    public String login() {
        log.info("======login====");
        return "admin/login";
    }

    @RequestMapping(value = {"/admin/login"}, method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password, ModelMap model, HttpSession session
    ) {
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
            User user = (User) subject.getPrincipal();
            session.setAttribute("username", user.getNickName());
            return redirect("/admin/index");
        } catch (AuthenticationException e) {
            model.put("admin/message", e.getMessage());
        }

        return "admin/login";
    }


    @RequestMapping(value = {"/admin/logout"}, method = RequestMethod.GET)
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return redirect("/admin/login");
    }

    @RequestMapping("/previlige/no")
    @ResponseBody
    public Map<String, String> page403() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "-1");
        map.put("message", "无权限访问");
        return map;
    }
}
