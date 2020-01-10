package com._520.crowdfunding.web.controller;

import com._520.crowdfunding.domain.TAdmin;
import com._520.crowdfunding.service.TAdminService;
import com.github.pagehelper.Page;
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
public class TAdminController {
    private Logger logger = LoggerFactory.getLogger(TAdminController.class);

    @Autowired
    private TAdminService adminService;

    /**
     * 处理登录后主页面的显示
     * @param pageNum       当前页
     * @param pageSize      每页的数量
     * @return              跳转
     */
    @RequestMapping("/admin/index")
    public String index(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                        @RequestParam(value = "pageSize", required = false, defaultValue = "2") Integer pageSize,
                        Model model){
        logger.debug("跳转到后台主界面 ");
        logger.info("pageNum = {}", pageNum);
        logger.info("pageSize = {}", pageSize);

        // 封装高级查询条件
        Map<String, Object> map = new HashMap<>();
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
