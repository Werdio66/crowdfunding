package com._520.crowdfunding.web.controller;

import com._520.crowdfunding.common.util.Const;
import com._520.crowdfunding.domain.TAdmin;
import com._520.crowdfunding.domain.TMenu;
import com._520.crowdfunding.service.TAdminService;
import com._520.crowdfunding.service.TMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HandleDispatherController {

    // 拿到日志对象
    private Logger logger = LoggerFactory.getLogger(HandleDispatherController.class);

    // 用户
    @Autowired
    private TAdminService adminService;

    // 菜单
    @Autowired
    private TMenuService menuService;

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

    // 注销
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        logger.debug("注销登录");
        if (session != null){
            session.invalidate();
        }
        return "redirect:/index";
    }

    // 跳转到菜单
    @RequestMapping("/main")
    public String main(HttpSession session) {
        // 查询所有的父菜单，将子菜单封装到父菜单中
        List<TMenu> menuList = menuService.listAll();
        session.setAttribute("menuList", menuList);
        return "main";
    }
    // 处理登录
    @RequestMapping("/doLogin")
    public String doLogin(String username, String password, HttpSession session, Model model){
        logger.debug("login..");
        logger.info("username = {}", username);
        logger.info("password = {}", password);

        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        // 处理登录
        try {
            TAdmin admin = adminService.checkLogin(map);
            // 将用户存放到session中
            session.setAttribute(Const.LOGIN_ADMIN, admin);
            return "redirect:/main";
        }catch (Exception e){
            logger.debug("errorMsg = {}", e.getMessage());
            model.addAttribute(Const.ERROR_MSG, e.getMessage());
            return "login";
        }



    }
}
