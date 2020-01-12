package com._520.crowdfunding.web.controller;

import com._520.crowdfunding.domain.TMenu;
import com._520.crowdfunding.service.TMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * 菜单
 */
@Controller
@RequestMapping("/menu")
public class TMenuController {

    @Autowired
    private TMenuService menuService;

    private Logger logger = LoggerFactory.getLogger(TMenuController.class);


    // 跳转到菜单模块页面
    @RequestMapping("/index")
    public String index(){

        return "menu/index";
    }

    // 修改菜单结点
    @ResponseBody
    @RequestMapping("/updateMenu")
    public Integer updateMenu(TMenu menu){
        logger.debug("menu = {}", menu);
        return menuService.updateMenu(menu);
    }

    @ResponseBody
    @RequestMapping("/deleteById")
    public Integer deleteById(Integer id){

        return menuService.deleteById(id);
    }
    //
    @ResponseBody
    @RequestMapping("/getMenuById")
    public TMenu getMenuById(Integer id){
        return menuService.getMenuById(id);
    }

    // 增加菜单结点
    @ResponseBody
    @RequestMapping("/addMenu")
    public Integer addMenu(TMenu menu){
        System.out.println(menu.getPid());
        return menuService.addMenu(menu);
    }

    // 查找菜单树，并转换为json格式
    @ResponseBody
    @RequestMapping("/loadTree")
    public List<TMenu> loadTree(){

        List<TMenu> menuList = menuService.listAllNotFormat();
        return menuList;
    }
}
