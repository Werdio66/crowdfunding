package com._520.crowdfunding.web.controller;

import com._520.crowdfunding.domain.TRole;
import com._520.crowdfunding.service.TRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 *  角色维护
 */
@Controller
@RequestMapping("/role")
public class TRoleController {

    @Autowired
    private TRoleService roleService;

    private Logger logger = LoggerFactory.getLogger(TRoleController.class);


    // 跳转到角色维护的主页面
    @RequestMapping("/index")
    public String index(){

        return "role/index";
    }


    @ResponseBody
    @RequestMapping("/addRole")
    public Integer addRole(TRole role){
        if ("".equals(role.getName())){
            return -1;
        }
        return roleService.add(role);
    }
    /**
     *      查询数据
     * @param pageNum       当前页
     * @param pageSize      每页数量
     * @param condition     查询条件
     * @return  将对象转换为json格式返回
     */
    @ResponseBody
    @RequestMapping("/loadDate")
    public PageInfo<TRole> loadDate(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", required = false, defaultValue = "2") Integer pageSize,
                                    @RequestParam(value = "condition", required = false, defaultValue = "")String condition){

        logger.debug("pageNum = {}, pageSize = {}", pageNum, pageSize);
        logger.debug("condition = {}", condition);
        // 线程绑定
        PageHelper.startPage(pageNum, pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("condition", condition);
        PageInfo<TRole> pageInfo = roleService.listAllTRole(map);
        logger.debug("pageInfo = {}", pageInfo);
        return pageInfo;
    }
}
