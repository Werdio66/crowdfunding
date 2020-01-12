package com._520.crowdfunding.service;

import com._520.crowdfunding.domain.TMenu;
import java.util.List;

public interface TMenuService {

    /**
     * 按照父子级别查找菜单
     */
    List<TMenu> listAll();

    /**
     *  直接查找菜单
     */
    List<TMenu> listAllNotFormat();

    /**
     *  增加菜单
     */
    Integer addMenu(TMenu menu);

    /**
     *  修改
     */
    Integer updateMenu(TMenu menu);

    /**
     *  通过id查询
     */
    TMenu getMenuById(Integer id);
}
