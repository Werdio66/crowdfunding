package com._520.crowdfunding.mapper;

import com._520.crowdfunding.domain.TPermissionMenu;
import com._520.crowdfunding.domain.TPermissionMenuExample;
import com._520.crowdfunding.domain.TPermissionMenuKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TPermissionMenuMapper {
    long countByExample(TPermissionMenuExample example);

    int deleteByExample(TPermissionMenuExample example);

    int deleteByPrimaryKey(TPermissionMenuKey key);

    int insert(TPermissionMenu record);

    int insertSelective(TPermissionMenu record);

    List<TPermissionMenu> selectByExample(TPermissionMenuExample example);

    TPermissionMenu selectByPrimaryKey(TPermissionMenuKey key);

    int updateByExampleSelective(@Param("record") TPermissionMenu record, @Param("example") TPermissionMenuExample example);

    int updateByExample(@Param("record") TPermissionMenu record, @Param("example") TPermissionMenuExample example);

    int updateByPrimaryKeySelective(TPermissionMenu record);

    int updateByPrimaryKey(TPermissionMenu record);
}