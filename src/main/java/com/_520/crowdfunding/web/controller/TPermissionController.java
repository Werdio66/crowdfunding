package com._520.crowdfunding.web.controller;

import com._520.crowdfunding.domain.TPermission;
import com._520.crowdfunding.service.TPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 权限管理
 */
@Controller
@RequestMapping("/permission")
public class TPermissionController {

    @Autowired
    private TPermissionService permissionService;

    // 跳转到主界面
    @RequestMapping("/index")
    public String index(){
        return "permission/index";
    }

    @ResponseBody
    @RequestMapping("/addPermission")
    public Integer addPermission(TPermission permission){
        return permissionService.addPermissionById(permission);
    }

    @ResponseBody
    @RequestMapping("/getPermissionById")
    public TPermission getPermissionById(Integer id){
        return permissionService.getPermissionById(id);
    }
    // 查询所有的权限
    @ResponseBody
    @RequestMapping("/loadPermissionTree")
    public List<TPermission> loadPermissionTree(){
        return permissionService.listAll();
    }

    @ResponseBody
    @RequestMapping("/updatePermission")
    public Integer updatePermission(TPermission permission){
        return permissionService.updatePermission(permission);
    }

    @ResponseBody
    @RequestMapping("/deleteById")
    public Integer deleteById(Integer id){
        return permissionService.deleteById(id);
    }
}
