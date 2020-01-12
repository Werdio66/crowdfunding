package com._520.crowdfunding.service.impl;

import com._520.crowdfunding.domain.TMenu;
import com._520.crowdfunding.domain.TMenuExample;
import com._520.crowdfunding.domain.TMenuKey;
import com._520.crowdfunding.mapper.TMenuMapper;
import com._520.crowdfunding.service.TMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TMenuServiceImpl implements TMenuService {

    @Autowired
    private TMenuMapper menuMapper;
    private Logger logger = LoggerFactory.getLogger(TMenuServiceImpl.class);

    // 查询所有的菜单
    @Override
    public List<TMenu> listAll() {

        // 存放父菜单
        List<TMenu> parentMenu = new ArrayList<>();
        //
        Map<Integer, TMenu> cache = new HashMap<>();
        // 查询所有的菜单
        List<TMenu> allMenu = menuMapper.selectByExample(null);
        logger.info("allMenu = {}", allMenu);
        // 查找父菜单
        for (TMenu menu : allMenu) {
            if (menu.getPid() == 0){
                parentMenu.add(menu);
                // 将父菜单存入map中，便于注入子菜单
                cache.put(menu.getId(), menu);
            }
        }
        // 设置子菜单
        for (TMenu menu : allMenu) {
            if (menu.getPid() != 0){
                // 取出子菜单的父菜单
                TMenu tMenu = cache.get(menu.getPid());
                // 将子菜单存入父菜单中
                tMenu.getChildren().add(menu);
            }
        }

        logger.info("parent =  {}", parentMenu);
        return parentMenu;
    }

    @Override
    public List<TMenu> listAllNotFormat() {
        TMenuExample example = new TMenuExample();
        return menuMapper.selectByExample(null);
    }

    @Override
    public Integer addMenu(TMenu menu) {
        return menuMapper.insertSelective(menu);
    }

    @Override
    public Integer updateMenu(TMenu menu) {
        return menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public TMenu getMenuById(Integer id) {
        TMenuKey key = new TMenuKey();
        key.setId(id);
        return menuMapper.selectByPrimaryKey(key);
    }

    @Override
    public Integer deleteById(Integer id) {
        TMenuKey key = new TMenuKey();
        key.setId(id);
        return menuMapper.deleteByPrimaryKey(key);
    }
}
