package com._520.crowdfunding.web.controller;

import com._520.crowdfunding.domain.TAdmin;
import com._520.crowdfunding.service.TAdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

/**
 *  处理用户的增删改查，登录后的主菜单页面
 */
@Controller
@RequestMapping("/admin")
public class TAdminController {
    private Logger logger = LoggerFactory.getLogger(TAdminController.class);

    @Autowired
    private TAdminService adminService;


    // 删除用户
    @RequestMapping("/doDelete")
    public String doDelete(Integer id, Integer pageNum){
        adminService.deleteById(id);
        return "redirect:/admin/index?pageNum=" + pageNum;
    }
    // 跳转到修改页面
    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id, Model model){
        logger.info("修改用户的id = {}", id);
        // 调用业务层得到指定id的用户
        TAdmin admin = adminService.getTAdminById(id);
        model.addAttribute("admin", admin);
        logger.info("跳转到update界面..");
        return "admin/update";
    }

    // 修改用户信息
    @RequestMapping("/doUpdate")
    public String doUpdate(TAdmin admin, Integer pageNum){
        logger.info("请求参数中的 admin = {}", admin);
        // 调用service完成修改
        adminService.updateTAdmin(admin);

        // 添加完成后去显示刚添加的数据
        return "redirect:/admin/index?pageNum=" + pageNum;
    }
    // 跳转到添加页面
    @RequestMapping("/toAdd")
    public String toAdd(){

        return "admin/add";
    }

    // 跳转到添加页面
    @RequestMapping("/doAdd")
    public String doAdd(TAdmin admin){
        logger.info("请求参数中的 admin = {}", admin);
        // 调用service完成添加
        adminService.saveTAdmin(admin);
        // 添加完成后去显示刚添加的数据
        return "redirect:/admin/index?pageNum=" + Integer.MAX_VALUE;
    }

    /**
     * 处理登录后主页面的显示
     * @param pageNum       当前页
     * @param pageSize      每页的数量
     * @param condition     查询条件
     * @return              跳转
     */
    @RequestMapping("/index")
    public String index(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                        @RequestParam(value = "pageSize", required = false, defaultValue = "2") Integer pageSize,
                        Model model,
                        @RequestParam(value = "condition", required = false, defaultValue = "")String condition){
        logger.debug("跳转到后台主界面 ");
        logger.info("pageNum = {}", pageNum);
        logger.info("pageSize = {}", pageSize);
        logger.info("condition = {}", condition);

        // 封装模糊查询条件
        Map<String, Object> map = new HashMap<>();
        map.put("condition", condition);
        // 设置页码和每页的数量，线程绑定
        PageHelper.startPage(pageNum, pageSize);
        // 调用业务层查询分页数据
        PageInfo<TAdmin> pageInfo = adminService.listAdminPage(map);
        logger.info("page = {}", pageInfo);
        // 将查询结果传递给index界面
        model.addAttribute("adminPage", pageInfo);
        return "admin/index";
    }
}
