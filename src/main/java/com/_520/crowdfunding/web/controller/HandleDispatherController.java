package com._520.crowdfunding.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HandleDispatherController {

    // 拿到日志对象
    Logger logger = LoggerFactory.getLogger(HandleDispatherController.class);

    // 跳转到index页面
    @RequestMapping("/index")
    public String index() {
        logger.debug("跳转到主系统页面");
        return "index";
    }

    // 跳转到登录页面
    @RequestMapping("/login")
    public String login() {
        logger.debug("跳转到登录页面");
        return "login";
    }

    // 处理登录
    @RequestMapping("/doLogin")
    public String doLogin(String username, String password){
        logger.debug("login..");
        logger.info("username = {}", username);
        logger.info("password = {}", password);
        return "main";
    }
}
