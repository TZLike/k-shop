package com.huatech.shop.module.account.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author leek
 * @Date 2019-07-17 上午10:38
 * @Version 1.0
 * @Description
 */
@Controller
@Slf4j
public class AdminIndexController {

    @RequestMapping("/")
    public String toIndex() {
        return "index";
    }

    @RequestMapping(value = {"/admin/", "admin/index"})
    public String index() {

        return "admin/index";
    }

    @RequestMapping(value = {"/welcome"})
    public String welcome() {

        return "admin/welcome";
    }
}
